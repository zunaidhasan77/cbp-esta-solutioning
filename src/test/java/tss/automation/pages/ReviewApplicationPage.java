package tss.automation.pages;

import org.openqa.selenium.By;
import tss.automation.utilities.ExcelReaderUtils;
import tss.automation.utilities.HelperMethods;
import net.serenitybdd.core.pages.PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;


public class ReviewApplicationPage extends PageObject {

    HelperMethods helper;
    ExcelReaderUtils readExcel;
    private By btn_confirm_confim1 = By.xpath("//button[text()='CONFIRM & CONTINUE' and @id='confirm0']");
    private By btn_confirm_confirm2 = By.xpath("//button[text()='CONFIRM & CONTINUE' and @id='confirm1']");
    private By btn_confirm_confirm3 = By.xpath("//button[text()='CONFIRM & CONTINUE' and @id='confirm2']");
    private By btn_confirm_confirm4 = By.xpath("//button[text()='CONFIRM & CONTINUE' and @id='confirm3']");
    private By btn_next = By.xpath("//button[text()='NEXT']");
    public void FillReviewApplicationPageDetails()  {
        find(btn_confirm_confim1).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_confim1));
        find(btn_confirm_confirm2).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_confirm2));
        find(btn_confirm_confirm3).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_confirm3));
        find(btn_confirm_confirm4).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_confirm_confirm4));
        find(btn_next).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        helper.clickOnElementJS(find(btn_next));
    }
}
