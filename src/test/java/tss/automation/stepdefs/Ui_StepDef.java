package tss.automation.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import tss.automation.pages.*;
import tss.automation.steps.Ui_Steps;
import tss.automation.utilities.ExcelReaderUtils;
import tss.automation.utilities.HelperMethods;
import tss.automation.utilities.ScenarioContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Scenario step defs, can use across multiple scenarios (test cases)
 *
 * @author Saikat Barua
 *
 */

public class Ui_StepDef {
    @Steps
    HelperMethods helper;

    @Steps
    ExcelReaderUtils readExcel;

    @Steps
    Ui_Steps uiSteps;

    ApplicantInformationPage passportpage;

    PersonalInformationPage personalInformationPage;
    TravelInformationPage travelInformationPage;

    EligibilityQuestionsPage eligibilityQuestionsPage;
    ReviewApplicationPage reviewApplicationPage;


    @Given("the user navigate to the ESTA application")
    public void the_user_navigate_to_the_esta_application() {
        uiSteps.the_esta_home_page_is_loaded();
    }

    @Given("Test data is loaded from Excel row {int}")
    public void loadExcelRow(int rowIndex) throws IOException {
        List<Map<String, String>> allRows = ExcelReaderUtils.read();  // default Sheet1
        Map<String, String> row = allRows.get(rowIndex);
        ScenarioContext.set("testData", row);
    }

    @When("the user enters Enter Applicant Information Details")
    public void the_user_enters_enter_applicant_information_details() throws IOException, InterruptedException {
        passportpage.FillPassportDetails();
    }

    @When("the User enters Personal Information Details")
    public void the_user_enters_personal_information_details() throws IOException {
        personalInformationPage.PersonalInformationPageDetails();
    }
    @When("the User enters Travel Information Details")
    public void the_user_enters_travel_information_details() throws IOException {
        travelInformationPage.TravelInformationPageDetails();
    }
    @When("the User enters Eligibility Questions Details")
    public void the_user_enters_eligibility_questions_details()  throws IOException, InterruptedException {
        eligibilityQuestionsPage.FillEligibilityQuestionsPage();
    }
    @When("the User enters Review Application Details")
    public void the_user_enters_review_application_details() {
        reviewApplicationPage.FillReviewApplicationPageDetails();
    }
    @When("the User enters Pay Details")
    public void the_user_enters_pay_details() {

    }


    @When("the user enters below data from excel while completing application:")
    public void the_user_enters_below_data_from_excel_while_completing_application(
            io.cucumber.datatable.DataTable dataTable) throws IOException {
     //   passportpage.FillPassportDetails();
    }

    @Given("the {string} home page is loaded")
    public void the_home_page_is_loaded(String url) {
        uiSteps.the_home_page_is_loaded(url);
    }

    @Given("the user navigate to the {string} page successfully as")
    public void the_user_navigate_to_the_page_successfully_as(String page, DataTable dataTable) {
        uiSteps.the_user_navigate_to_the_page_successfully_as(page, dataTable);
        uiSteps.the_user_proceeds_with_selecting_radio_buttons_for_disclaimer_and_promotion_Act();
    }

    @When("the user uploads the passport {string} using {string}")
    public void the_user_uploads_the_passport_using(String passport, String option) {
        uiSteps.the_user_uploads_the_passport_using(passport, option);
    }

    @Then("passport has been uploaded successfully")
    public void passport_has_been_uploaded_successfully() {
        uiSteps.passport_has_been_uploaded_successfully();
    }

    @Then("system displays the error message as {string}")
    public void system_displays_the_error_message_as(String message) {
        uiSteps.system_displays_the_error_message_as(message);
    }

    @Then("the user is performing some actions")
    public void theUserIsPerformingSomeActions() {
    }
}
