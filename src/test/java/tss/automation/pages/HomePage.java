package tss.automation.pages;

import java.io.File;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import io.cucumber.datatable.DataTable;
import jline.internal.Log;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.WebDriverWait;
import tss.automation.utilities.HelperMethods;
import net.serenitybdd.annotations.DefaultUrl;

/**
 * Page Object, can use across multiple scenarios (test cases)
 *
 * @author Saikat Barua
 *
 */

@DefaultUrl("https://esta.cbp.dhs.gov")
public class HomePage extends PageObject {

    HelperMethods helper;
    // PAGE OBJECT are loaded here

    private By btn_create_continue_application_list =
            By.cssSelector("button.dropdown-toggle span:not(.caret)");
    private By lnk_application_list =
            By.cssSelector("ul.dropdown-menu.create-app-dropdown-menu li span");
    private By radiobox_list = By.cssSelector(".radio .mdl-radio__outer-circle");
    private By btn_next = By.xpath("//button[contains(.,'NEXT')]");
    private By btn_upload_your_password = By.cssSelector("#uploadPassportModal button.btn");
    private By btn_from_gallery =
            By.cssSelector("div.N1kHRG_start-window div.N1kHRG_buttons button:nth-child(2)");
    private By btn_confirm_continue = By.xpath("//button[contains(.,'CONFIRM & CONTINUE')]");
    public By err_msg_passport = By.cssSelector(
            "#ocrFailedModal > div > div > div.modal-body > div.alert.alert-danger.update > strong");
    public By btn_continue_error_modal = By.cssSelector("#ocrFailedModal button.btn");
    public By btn_applicantinfo_list =
            By.cssSelector("#applicantForm > form > app-form-nav button");
    public By btn_add_to_my_application =
            By.xpath("//button[text()='ADD TO MY APPLICATION']");

    public void userSelectsPageFromEstaAppModal(String page, DataTable dataTable) {
        // Step 1: Click the dropdown toggle that matches the given page
        helper.clickOnElementByTextfromListElements(findAll(btn_create_continue_application_list),
                page);

        // Step 2: Click the actual item from the dropdown menu
        if (dataTable != null && !dataTable.asLists().isEmpty()) {
            List<String> valuesToSelect = helper.getAllKeysFromTable(dataTable);

            for (String value : valuesToSelect) {
                helper.clickOnElementByTextfromListElements(findAll(lnk_application_list), value);
                Log.info("Dropdown option selected: " + value);
            }
        }
    }

    public void userSelectsRadioButtonForDisclaimberAndPromotion() {
        find(btn_confirm_continue).waitUntilClickable().click();
        findAll(radiobox_list).get(0).waitUntilVisible();
        helper.clickOnElementJS(findAll(radiobox_list).get(0));
        helper.clickOnElementJS(findAll(radiobox_list).get(2));
        find(btn_next).waitUntilVisible();
        helper.clickOnElementJS(find(btn_next));
    }

    public void verifyPassportUploadedSuccessfully() {
        find(err_msg_passport).shouldNotBeCurrentlyVisible();
    }

    public void userUploadsPassportImage(String passport, String option) {
        // Step 1: Click the "Upload your passport" button
        find(btn_upload_your_password).waitUntilClickable().click();

        // Step 2: Access the shadow root of <document-reader>
        WebElement shadowHost = getDriver().findElement(By.cssSelector("document-reader"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Step 3: Find the hidden file input
        WebElement fileInput = shadowRoot.findElement(By.cssSelector("input[type='file']"));

        // Step 4: Make input visible using JavaScript
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';",
                fileInput
        );

        // Step 5: Upload file
        File imageFile = new File("src/test/resources/files/" + passport);
        if (!imageFile.exists()) {
            throw new RuntimeException("File not found: " + imageFile.getAbsolutePath());
        }

        fileInput.sendKeys(imageFile.getAbsolutePath());

        // Step 6: Wait for UI confirmation
        findAll(btn_applicantinfo_list).get(1).waitUntilVisible();


        find(btn_add_to_my_application).withTimeoutOf(Duration.ofSeconds(90)).waitUntilVisible();
        find(btn_add_to_my_application).waitUntilClickable().click();
        Log.info("Passport uploaded successfully");
    }

    public void verifyErrorMessage(String message) {
        find(btn_continue_error_modal).waitUntilVisible();
        find(err_msg_passport).waitUntilPresent();
        String val = find(err_msg_passport).getText();
        Log.info("Error message displays as: " + val);
        find(err_msg_passport).shouldContainText(message);
        Log.info("Error message displays as expected");
    }


}
