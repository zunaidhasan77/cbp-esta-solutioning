package tss.automation.stepdefs;

import org.json.simple.parser.ParseException;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import tss.automation.steps.Accessibility_Steps;
import tss.automation.utilities.HelperMethods;

/**
 * Scenario step defs, can use across multiple scenarios (test cases)
 *
 * @author Saikat Barua
 *
 */
 
public class Accessibility_StepDef {

    @Steps
    HelperMethods helper;

    @Steps
    Accessibility_Steps accessibilitySteps;

    @Then("verify there is no violations should occur")
    public void verify_there_is_no_violations_should_occur() throws ParseException {
        accessibilitySteps.verify_there_is_no_violations_should_occur();
    }

    @Given("axe has been injected")
    public void axe_has_been_injected() {
        accessibilitySteps.axe_has_been_injected();
    }

    @When("an axe scan is executed for AA WCAG level")
    public void an_axe_scan_is_executed_for_aa_wcag_level() {
        accessibilitySteps.an_axe_scan_is_executed_for_aa_wcag_level();
    }
}


