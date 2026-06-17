package tss.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import tss.automation.utilities.ExcelReaderUtils;
import tss.automation.utilities.HelperMethods;
import tss.automation.utilities.ScenarioContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PersonalInformationPage extends PageObject {

    HelperMethods helper;
    ExcelReaderUtils readExcel;
    private By radio_hasAlias_yes = By.xpath("//label//input[@id='hasAliasRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By txt_alias_lastName = By.cssSelector("#aliasLastName_0");
    private By txt_alias_firstName = By.cssSelector("#aliasFirstName_0");



    private By radio_hasAlias_no = By.xpath("//label//input[@id='hasAliasRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_hasAddTravel_yes = By.xpath("//label//input[@id='hasAddlTravelDocRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_hasAddTravel_no = By.xpath("//label//input[@id='hasAddlTravelDocRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By txt_line1_contactAddress = By.cssSelector("#line1_contactAddress");
    private By txt_line2_contactAdress = By.cssSelector("#line2_contactAddress");
    private By txt_apt_contactAddress = By.cssSelector("#apt_contactAddress");
    private By txt_city_contactAddress = By.cssSelector("#city_contactAddress");
    private By txt_state_contactAddress = By.cssSelector("#state_contactAddress");
    private By dropDown_country = By.cssSelector("#country_contactAddress");
    private By dropdown_telephone = By.cssSelector("#type_contactPhone_0");
    private By dropdown_countryCode = By.cssSelector("#cc_contactPhone_0");
    private By txt_state_phoneNumber = By.cssSelector("#number_contactPhone_0");
    private By radio_membership_yes = By.xpath("//label//input[@id='hasGeMembershipRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_membership_no = By.xpath("//label//input[@id='hasGeMembershipRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By txt_first_parent_lastName = By.cssSelector("#firstParentLastName");
    private By txt_first_parent_firstName = By.cssSelector("#firstParentFirstName");
    private By txt_second_parent_lastName = By.cssSelector("#secondParentLastName");
    private By txt_second_parent_firstName = By.cssSelector("#secondParentFirstName");
    private By radio_employment_yes = By.xpath("//label//input[@id='hasEmploymentRadiosYes']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By radio_employment_no = By.xpath("//label//input[@id='hasEmploymentRadiosNo']//following-sibling::span[contains(@class,'mdl-radio__ripple-container')]");
    private By btn_next = By.xpath("//button[text()='NEXT']");

    public  void PersonalInformationPageDetails() throws IOException {
        Map<String, String> passport = (Map<String, String>) ScenarioContext.get("testData");
        helper.selectRadioOption(passport.get("ALIAS_NAME_Q1"),find(radio_hasAlias_yes),find(radio_hasAlias_no));
        helper.sendText( passport.get("ALIASLASTNAME"),find(txt_alias_lastName));
        helper.sendText( passport.get("ALIASFIRSTNAME"),find(txt_alias_firstName));

        helper.selectRadioOption(passport.get("OTHER_PASSPORT_NATIONAL_IDENTITY_CARD_Q2"),find(radio_hasAddTravel_yes),find(radio_hasAddTravel_no));

        helper.sendText( passport.get("ADDRESS_STREET_1"),find(txt_line1_contactAddress));
        helper.sendText( passport.get("ADDRESS_STREET_2"),find(txt_line2_contactAdress));
        helper.sendText( passport.get("ADDRESS_APT_SUITE"),find(txt_apt_contactAddress));
        helper.sendText( passport.get("ADDRESS_CITY"),find(txt_city_contactAddress));
        helper.sendText( passport.get("ADDRESS_STATE_PROVINCE"),find(txt_state_contactAddress));
        helper.selectDropdownOptionByText(find(dropDown_country),passport.get("ADDRESS_COUNTRY"));
        helper.selectDropdownOptionByText(find(dropdown_telephone),passport.get("TELEPHONE_TYPE"));
        helper.selectDropdownOptionByText(find(dropdown_countryCode),passport.get("TELEPHONE_COUNTRY_CODE"));
        helper.sendText( passport.get("PHONE_NUMBER"),find(txt_state_phoneNumber));
        helper.selectRadioOption(passport.get("GLOBAL_ENTRY_PROGRAM_Q4"),find(radio_membership_yes),find(radio_membership_no));
       // PARENT1_FAMILYNAME	PARENT1_GIVENNAME	PARENT2_FAMILYNAME	PARENT2_GIVENNAME	EMPLOYMENT_INFO_Q5	TRANSIT_ANOTHER_COUNTRY_Q1	US_POINT_CONTACT_INFO_NAME	US_POINT_CONTACT_INFO_STREET_1	US_POINT_CONTACT_INFO_STREET_2	US_POINT_CONTACT_INFO_ADDRESS_APT_SUITE	US_POINT_CONTACT_INFO_ADDRESS_CITY	US_POINT_CONTACT_INFO_ADDRESS_STATE_PROVINCE	US_POINT_CONTACT_INFO_ADDRESS_COUNTRY_CODE

        helper.sendText( passport.get("PARENT1_FAMILYNAME"),find(txt_first_parent_lastName));
        helper.sendText( passport.get("PARENT1_GIVENNAME"),find(txt_first_parent_firstName));
        helper.sendText( passport.get("PARENT2_FAMILYNAME"),find(txt_second_parent_lastName));
        helper.sendText( passport.get("PARENT2_GIVENNAME"),find(txt_second_parent_firstName));
        helper.selectRadioOption(passport.get("EMPLOYMENT_INFO_Q5"),find(radio_employment_yes),find(radio_employment_no));
        helper.clickOnElementJS(find(btn_next));








    }
}