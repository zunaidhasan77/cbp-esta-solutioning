package tss.automation.utilities;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.JavascriptExecutor;
import io.cucumber.datatable.DataTable;
import jline.internal.Log;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

/**
 * Helper methods, can use across multiple scenarios (test cases)
 *
 * @author Saikat Barua
 *
 */

public class HelperMethods extends PageObject {

    public void scrollIntoViewJS(WebElementFacade webElement) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                webElement);
        Log.info("JS Scrolled into an Element" + "\n");
    }

    public void clickOnElementJS(WebElementFacade webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", webElement);
        Log.info("JS Clicked on an element: " + webElement + "\n");
    }
    public void selectRadioOption(String value, WebElementFacade yesElement, WebElementFacade noElement) {
        if (value.equalsIgnoreCase("Yes")) {
            if (!yesElement.isSelected() && yesElement.isEnabled()) {
                clickOnElementJS(yesElement);
            }
        } else if (value.equalsIgnoreCase("No")) {
            if (!noElement.isSelected() && noElement.isEnabled()) {
                clickOnElementJS(noElement);
            }
        } else {
            throw new IllegalArgumentException("Invalid value: " + value + ". Expected 'Yes' or 'No'.");
        }
    }

    public void sendText(String text, WebElementFacade element){
        element.clear();
        element.sendKeys(text);
    }

    public String clickOnElementByTextfromListElements(List<WebElementFacade> webelements,
            String text) {
        String value = null;

        List<WebElementFacade> links = webelements;
        for (int i = 0; i < links.size(); i++) {
            links.get(i).waitUntilPresent();
            if (links.get(i).isPresent()) {
                if (links.get(i).getText().trim().toUpperCase().trim()
                        .contains(text.toUpperCase())) {
                    value = links.get(i).getText().trim().toUpperCase();
                    Serenity.takeScreenshot();
                    links.get(i).waitUntilPresent();
                    scrollIntoViewJS(links.get(i));
                    clickOnElementJS(links.get(i));

                    Log.info(value + " => has been selected from the list" + "\n");
                    break;
                }
            }
        }
        Serenity.takeScreenshot();
        return value;
    }

    /**
     * Selects a dropdown option by visible text if available.
     * Designed for Serenity with robust fallback, logging, and optional screenshot.
     *
     * @param dropdown The dropdown WebElementFacade
     * @param textToSelect The visible text to select
     * @return true if selection was successful, false otherwise
     */
    public boolean selectDropdownOptionByText(WebElementFacade dropdown, String textToSelect) {
        try {
            dropdown.waitUntilVisible(); // Wait for visibility
            Select select = new Select(dropdown);

            List<WebElement> options = select.getOptions();
            boolean matchFound = false;

            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(textToSelect.trim())) {
                    select.selectByVisibleText(option.getText().trim());
                    matchFound = true;

                  //  Serenity.takeScreenshot(); // Optional: Screenshot for reporting
                    Log.info("Selected '" + textToSelect + "' from dropdown.");
                    break;
                }
            }

            if (!matchFound) {
                Log.warn("Value '" + textToSelect + "' was not found in the dropdown options.");
            }

            return matchFound;

        } catch (UnexpectedTagNameException e) {
            Log.error("Provided element is not a <select> tag: " + e.getMessage());
        } catch (Exception e) {
            Log.error("Error selecting dropdown value: " + e.getMessage());
        }

        return false;
    }

    public List<String> getAllKeysFromTable(DataTable dtValues) {
        List<String> keys = new ArrayList<String>();
        for (String key : dtValues.asMap().keySet()) {
            keys.add(key);
            Log.info("Datatable key: " + key + "\n");
        }
        return keys;
    }
    /**
     * Selects a radio button or checkbox by value from a list of WebElementFacade elements.
     * Logs the action, takes a screenshot, and returns success status.
     *
     * @param options List of radio buttons or checkboxes (as WebElementFacade)
     * @param valueToSelect The target value to match against the "value" attribute
     * @return true if the option was selected, false otherwise
     */
    public static boolean clickRadioOrCheckbox1(List<WebElementFacade> options, String valueToSelect) {
        if (options == null || options.isEmpty()) {
            Log.warn("No radio or checkbox options provided.");
            return false;
        }

        if (valueToSelect == null || valueToSelect.trim().isEmpty()) {
            Log.warn("No value provided to select from radio/checkbox group.");
            return false;
        }

        for (WebElementFacade option : options) {
            try {
                String actualValue = option.getValue(); // Serenity-style attribute retrieval
                if (actualValue != null && actualValue.trim().equalsIgnoreCase(valueToSelect.trim())) {
                    if (option.isSelected()) {
                        Log.info("Option with value '" + valueToSelect + "' is already selected.");
                        return true;
                    }

                    if (!option.isEnabled()) {
                        Log.warn("Option with value '" + valueToSelect + "' is disabled.");
                        return false;
                    }

                    option.click();
                    Log.info("Selected radio/checkbox with value: " + valueToSelect);
                    Serenity.takeScreenshot(); // capture screenshot after selection
                    return true;
                }
            } catch (Exception e) {
                Log.error("Error selecting radio/checkbox with value '" + valueToSelect + "': " + e.getMessage(), e);
            }
        }

        Log.warn("Radio/checkbox with value '" + valueToSelect + "' not found.");
        return false;
    }


    public static void clickRadioOrCheckbox(List<WebElementFacade> radioOrcheckbox, String value) {

        String actualValue;

        for (WebElement el : radioOrcheckbox) {
            actualValue = el.getAttribute("id").trim();
            if (!el.isSelected() && el.isEnabled() && actualValue.contains(value)) {
                el.click();
                break;
            }
        }
    }

}



