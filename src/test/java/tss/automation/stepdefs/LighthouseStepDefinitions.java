package tss.automation.stepdefs;

import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import tss.automation.utilities.LighthouseRunner;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class LighthouseStepDefinitions extends PageObject {

    @Then("run lighthouse audit for {string}")
    public void runLighthouseAudit(String targetUrl) {
        // String currentUrl = getDriver().getCurrentUrl();
        // String targetUrl;

        // if (currentUrl != null && !currentUrl.isEmpty()
        // && currentUrl.contains("esta.cbp.dhs.gov")) {
        // targetUrl = currentUrl;
        // } else {
        // targetUrl = fallbackUrl;
        // }

        // Generate unique filename based on target URL
        String reportBase = "target/lighthouse/" + sanitizeUrlForFilename(targetUrl);
        LighthouseRunner.runAudit(targetUrl, reportBase);

        File htmlFile = new File(reportBase + ".report.html");

        if (htmlFile.exists()) {
            Serenity.recordReportData().withTitle("📂 Lighthouse Report Path: Open in browser")
                    .andContents(htmlFile.getAbsolutePath());
        } else {
            throw new RuntimeException("Lighthouse HTML report not found: " + htmlFile.getPath());
        }
    }


    private String sanitizeUrlForFilename(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath().replaceAll("[^a-zA-Z0-9]", "-");
            if (path.isEmpty())
                path = "home";
            String host = uri.getHost().replaceAll("[^a-zA-Z0-9]", "-");
            return host + path;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URL format: " + url, e);
        }
    }
}
