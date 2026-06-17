package tss.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import tss.automation.utilities.ExcelReaderUtils;
import tss.automation.utilities.HelperMethods;
import tss.automation.utilities.ScenarioContext;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class TravelInformationPage extends PageObject {

    HelperMethods helper;
    ExcelReaderUtils readExcel;

    private By txt_emergency_familyName = By.cssSelector("#emergencyFamilyName");
    private By txt_emergency_firstName = By.cssSelector("#emergencyFirstName");
    private By txt_emergency_email = By.cssSelector("#emergencyEmail");
    private By dropdown_emergency_emergencyPhone = By.cssSelector("#cc_emergencyPhone");
    private By txt_emergency_phone = By.cssSelector("#number_emergencyPhone");
    private By radio_transitAnotherCountry_yes = By.xpath("//label//input[@id='isTransitToAnotherCountryRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_transitAnotherCountry_no = By.xpath("//label//input[@id='isTransitToAnotherCountryRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By txt_poc_name = By.cssSelector("#poc_name");
    private By txt_line1_contactAddress = By.cssSelector("#line1_contactAddress");
    private By txt_city_contactAddress = By.cssSelector("#city_contactAddress");
    private By dropdown_state_contact_address = By.cssSelector("#state_contactAddress");
    private By dropdown_country_code = By.cssSelector("#cc_contactPhone");
    private By txt_contact_phone = By.cssSelector("#number_contactPhone");
    private By radio_sameAddressRadio_yes = By.xpath("//label//input[@id='sameAddressRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_sameAddressRadio_no = By.xpath("//label//input[@id='sameAddressRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    public By btn_next = By.xpath("//button[text()='NEXT']");


    public  void TravelInformationPageDetails() throws IOException {
        Map<String, String> passport = (Map<String, String>) ScenarioContext.get("testData");
        find(radio_transitAnotherCountry_yes).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.selectRadioOption(passport.get("TRANSIT_ANOTHER_COUNTRY_Q1"),find(radio_transitAnotherCountry_yes),find(radio_transitAnotherCountry_no));
        helper.sendText(passport.get("US_POINT_CONTACT_INFO_NAME"), find(txt_poc_name));
        helper.sendText(passport.get("US_POINT_CONTACT_INFO_STREET_1"), find(txt_line1_contactAddress));
        helper.sendText(passport.get("US_POINT_CONTACT_INFO_ADDRESS_CITY"), find(txt_city_contactAddress));
        helper.selectFromDropdown(find(dropdown_state_contact_address),passport.get("US_POINT_CONTACT_INFO_ADDRESS_STATE_PROVINCE") );
        helper.selectFromDropdown(find(dropdown_country_code),passport.get("US_POINT_CONTACT_INFO_ADDRESS_COUNTRY_CODE") );
        helper.sendText(passport.get("US_POINT_CONTACT_INFO_ADDRESS_PHONE_NUMBER"), find(txt_contact_phone));
        helper.selectRadioOption(passport.get("ADDRESS_WHILE_IN_US_Q2"),find(radio_sameAddressRadio_yes),find(radio_sameAddressRadio_no));
        helper.sendText(passport.get("EMERGENCY_FAMILY_NAME"), find(txt_emergency_familyName));
        helper.sendText(passport.get("EMERGENCY_FIRST_GIVEN_NAME"), find(txt_emergency_firstName));
        helper.sendText(passport.get("EMERGENCY_EMAIL_ADDRESS"), find(txt_emergency_email));
        helper.selectFromDropdown(find(dropdown_emergency_emergencyPhone),passport.get("EMERGENCY_COUNTRY_CODE") );
        helper.sendText(passport.get("EMERGENCY_PHONE_NUMER"), find(txt_emergency_phone));
        helper.clickOnElementJS(find(btn_next));


    }






}
