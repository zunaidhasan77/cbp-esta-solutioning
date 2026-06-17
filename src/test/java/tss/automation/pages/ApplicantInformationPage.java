package tss.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import tss.automation.utilities.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ApplicantInformationPage extends PageObject {

    HelperMethods helper;
    ExcelReaderUtils readExcel;

    private By txt_last_name = By.cssSelector("#applicantLastName");
    private By txt_first_name = By.cssSelector("#applicantFirstName");
    private By txt_passport_num = By.cssSelector("#passportNum");
    private By dropdown_issue_country = By.cssSelector("#issueCountry");
    private By dropdown_issue_day = By.cssSelector("#day_issueDate");
    private By dropdown_issue_month = By.cssSelector("#month_issueDate");
    private By dropdown_issue_year = By.cssSelector("#year_issueDate");
    private By dropdown_expiration_day = By.cssSelector("#day_expDate");
    private By dropdown_expiration_month = By.cssSelector("#month_expDate");
    private By dropdown_expiration_year = By.cssSelector("#year_expDate");
    private By dropdown_nationality = By.cssSelector("#citizenshipCountry");
    private By dropdown_gender = By.cssSelector("#gender");
    private By dropdown_birthday_day = By.cssSelector("#day_birthday");
    private By dropdown_birthday_month = By.cssSelector("#month_birthday");
    private By dropdown_birthday_year = By.cssSelector("#year_birthday");
    private By txt_birth_city = By.cssSelector("#birthCity");
    private By txt_birth_country = By.cssSelector("#birthCountry");
    private By genderOptions = By.cssSelector("input[name='otherCitizenshipRadios']");
    private By radio_citizenshipNo = By.xpath("//label//input[@type='radio' and @id='otherCitizenshipRadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_past_citizenshipNo = By.xpath("//input[@id='pastCitizenshipRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_citizenshipYes = By.xpath("//label//input[@type='radio' and @id='otherCitizenshipRadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_past_citizenshipYes = By.xpath("//input[@id='pastCitizenshipRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");

    private By txt_contact_email = By.cssSelector("#contactEmail");
    private By txt_contact_email_confirm = By.cssSelector("#contactEmailConfirm");
    private By btn_next = By.xpath("//button[text()='NEXT']");
    private By btn_confirm_continue = By.xpath("//button[text()='CONFIRM & CONTINUE' and @type='submit']");
    private By btn_send_code = By.xpath("//button[text()='Send Code' and @type='submit']");
    private By btn_submit_code = By.xpath("//button[text()='Submit Code' and @type='submit']");
    private By btn_email_otp = By.xpath("//input[@id='eCode' and @type='text']");
    private By getpassportNumber=By.xpath("//b[text()='Passport Number']/parent::div");
    // PAGE OBJECT are loaded here
    public void FillPassportDetails() throws IOException, InterruptedException {
//List<Map<String,String>> passportDetails = readExcel.read();
//   Map<String, String> passport = passportDetails.get(0);
        Map<String, String> passport = (Map<String, String>) ScenarioContext.get("testData");

//        find(txt_last_name).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
//       String fullText= find(getpassportNumber).getText();
      //  String passportNumber = fullText.replace("Passport Number", "").trim();
      //  GlobalContext.set("passportNumberValue", passportNumber);
  //      helper.sendText(passport.get("LASTNAME"), find(txt_last_name));
  //      helper.sendText(passport.get("FIRSTNAME"), find(txt_first_name));
//            helper.sendText(passport.get("PASSPORTNUMBER"),find(txt_passport_num));
 //       helper.selectDropdownOptionByText(find(dropdown_issue_country), passport.get("PASSPORTCOUNTRYOFISSUE"));

//        helper.selectDropdownOptionByText(find(dropdown_expiration_day), passport.get("PASSPORTEXPIRATIONDAY"));
//        helper.selectDropdownOptionByText(find(dropdown_expiration_month), passport.get("PASSPORTEXPIRATIONMONTH"));
//        helper.selectDropdownOptionByText(find(dropdown_expiration_year), passport.get("PASSPORTEXPIRATIONYEAR"));
//        helper.selectDropdownOptionByText(find(dropdown_nationality), passport.get("COUNTRY_OF_CITIZENSHIP"));
//        helper.selectDropdownOptionByText(find(dropdown_gender), passport.get("GENDER"));
//        helper.selectDropdownOptionByText(find(dropdown_birthday_day), passport.get("DOB_DAY"));
//        helper.selectDropdownOptionByText(find(dropdown_birthday_month), passport.get("DOB_MONTH"));
//        helper.selectDropdownOptionByText(find(dropdown_birthday_year), passport.get("DOB_YEAR"));
        helper.selectDropdownOptionByText(find(dropdown_issue_day), passport.get("PASSPORTISSUEDAY"));
        helper.selectDropdownOptionByText(find(dropdown_issue_month), passport.get("PASSPORTISSUEMONTH"));
        helper.selectDropdownOptionByText(find(dropdown_issue_year), passport.get("PASSPORTISSUEYEAR"));
        helper.sendText(passport.get("CITY_OF_BIRTH"), find(txt_birth_city));
        helper.selectDropdownOptionByText(find(txt_birth_country), passport.get("COUNTRY_OF_BIRTH"));
        helper.sendText(passport.get("EMAIL_ADDRESS"), find(txt_contact_email));
        helper.sendText(passport.get("CONFIRM_EMAIL_ADDRESS"), find(txt_contact_email_confirm));
        find(radio_citizenshipNo).withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        helper.selectRadioOption(passport.get("OTHER_CITIZENSHIP_NATIONALITY_Q1"), find(radio_citizenshipYes), find(radio_citizenshipNo));
        helper.selectRadioOption(passport.get("PREVIOUS_CITIZENSHIP_NATIONALITY_Q2"), find(radio_past_citizenshipYes), find(radio_past_citizenshipNo));

        helper.clickOnElementJS(find(btn_next));
        find(btn_confirm_continue).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_continue));
        find(btn_confirm_continue).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_continue));
        find(btn_confirm_continue).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_continue));
        find(btn_confirm_continue).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_continue));
        EmptyInbox.emptyEmail();
        find(btn_send_code).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_send_code));
    //    Thread.sleep(16000);
        String otp = FetchLatestESTACodeEmail.findLatest4digitCode();
        System.out.println("Generated OTP: " + otp);
        find(btn_email_otp).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.sendText(otp, find(btn_email_otp));

        find(btn_submit_code).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_submit_code));


        //   WebElement saveButton = driver.findElement(By.id("btnSave"));
        //  saveButton.click();


    }
}