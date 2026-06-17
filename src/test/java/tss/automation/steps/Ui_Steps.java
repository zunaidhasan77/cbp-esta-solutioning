package tss.automation.steps;

import org.openqa.selenium.Dimension;
import io.cucumber.datatable.DataTable;
import jline.internal.Log;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import tss.automation.pages.HomePage;
import tss.automation.utilities.HelperMethods;

/**
 * Scenario steps, can use across multiple scenarios (test cases)
 *
 * @author Saikat Barua
 *
 */

public class Ui_Steps extends ScenarioSteps {

        HelperMethods helper;
        HomePage homepage;

        @Step
        public void the_home_page_is_loaded(String url) {
                getDriver().get(url);
                getDriver().manage().window().setSize(new Dimension(1800, 1240));
                Log.info("Window has been maximized to the resolution => 1800 X 1240" + "\n");
                Log.info("Login page is loaded");
        }

        @Step
        public void the_esta_home_page_is_loaded() {
                homepage.open();
               // getDriver().manage().window().setSize(new Dimension(1800, 1240));
                Log.info("Window has been maximized to the resolution => 1800 X 1240" + "\n");
                Log.info("Login page is loaded");
        }

        @Step
        public void the_user_navigate_to_the_page_successfully_as(String page,
                        DataTable dataTable) {
                homepage.userSelectsPageFromEstaAppModal(page, dataTable);
        }

        @Step
        public void the_user_proceeds_with_selecting_radio_buttons_for_disclaimer_and_promotion_Act() {
                homepage.userSelectsRadioButtonForDisclaimberAndPromotion();
        }

        @Step
        public void passport_has_been_uploaded_successfully() {
                homepage.verifyPassportUploadedSuccessfully();
        }

        @Step
        public void the_user_uploads_the_passport_using(String passport, String option) {
                homepage.userUploadsPassportImage(passport, option);
        }

        @Step
        public void system_displays_the_error_message_as(String message) {
                homepage.verifyErrorMessage(message);
        }
}
