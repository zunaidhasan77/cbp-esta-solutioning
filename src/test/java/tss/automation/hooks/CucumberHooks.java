package tss.automation.hooks;

import java.util.Collection;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import tss.automation.utilities.ExcelReader;
import tss.automation.utilities.ScenarioContext;

public class CucumberHooks {

//    private static final String EXCEL_PATH = "src/test/resources/files/test_data.xlsx";
//
//    @After
//    public void updateTestStatusInExcel(Scenario scenario) {
//        String testId = ScenarioContext.getTestId();
//        if (testId == null)
//            return;
//
//        Collection<String> tags = scenario.getSourceTagNames();
//        String status;
//
//        if (tags.contains("@manual")) {
//            status = "Manual";
//        } else if (tags.contains("@pending")) {
//            status = "Pending";
//        } else if (tags.contains("@ignore")) {
//            status = "Ignored";
//        } else {
//            status = scenario.isFailed() ? "Fail" : "Pass";
//        }
//
//        ExcelReader.updateTestStatusInExcel(EXCEL_PATH, testId, status);
//        ScenarioContext.clear();
//    }
}
