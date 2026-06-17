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

public class EligibilityQuestionsPage extends PageObject {

    HelperMethods helper;
    ExcelReaderUtils readExcel;

    private By radio_q1RadioYes = By.xpath("//label//input[@type='radio' and @id='q1RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q1RadioNo = By.xpath("//label//input[@type='radio' and @id='q1RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q2RadioYes = By.xpath("//label//input[@type='radio' and @id='q2RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q2RadioNo = By.xpath("//label//input[@type='radio' and @id='q2RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q3RadioYes = By.xpath("//label//input[@type='radio' and @id='q3RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q3RadioNo = By.xpath("//label//input[@type='radio' and @id='q3RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q4RadioYes = By.xpath("//label//input[@type='radio' and @id='q4RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q4RadioNo = By.xpath("//label//input[@type='radio' and @id='q4RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q5RadioYes = By.xpath("//label//input[@type='radio' and @id='q5RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q5RadioNo = By.xpath("//label//input[@type='radio' and @id='q5RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q6RadioYes = By.xpath("//label//input[@type='radio' and @id='q6RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q6RadioNo = By.xpath("//label//input[@type='radio' and @id='q6RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q7RadioYes = By.xpath("//label//input[@type='radio' and @id='q7RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q7RadioNo = By.xpath("//label//input[@type='radio' and @id='q7RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q8RadioYes = By.xpath("//label//input[@type='radio' and @id='q8RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q8RadioNo = By.xpath("//label//input[@type='radio' and @id='q8RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q9RadioYes = By.xpath("//label//input[@type='radio' and @id='q9RadiosYes']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By radio_q9RadioNo = By.xpath("//label//input[@type='radio' and @id='q9RadiosNo']//following-sibling::span[@class='mdl-radio__outer-circle']");
    private By checkbox_eligibility  =By.xpath(" //label//input[@type='checkbox' and @id='certCheck']//following-sibling::span[contains(@class,'mdl-checkbox__ripple-container')]");
////button[text()='Continue with new application' and @type='button']
    public By btn_next = By.xpath("//button[text()='NEXT']");

    // PAGE OBJECT are loaded here
    public void FillEligibilityQuestionsPage() throws IOException, InterruptedException {

        Map<String, String> passport = (Map<String, String>) ScenarioContext.get("testData");
        find(radio_q1RadioYes).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q1"),find(radio_q1RadioYes),find(radio_q1RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q2"),find(radio_q2RadioYes),find(radio_q2RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q3"),find(radio_q3RadioYes),find(radio_q3RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q4"),find(radio_q4RadioYes),find(radio_q4RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q5"),find(radio_q5RadioYes),find(radio_q5RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q6"),find(radio_q6RadioYes),find(radio_q6RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q7"),find(radio_q7RadioYes),find(radio_q7RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q8"),find(radio_q8RadioYes),find(radio_q8RadioNo));
        helper.selectRadioOption(passport.get("ELIGIBILITY_Q9"),find(radio_q9RadioYes),find(radio_q9RadioNo));
        helper.clickOnElementJS(find(checkbox_eligibility));
        find(btn_next).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_next));



    }
}
