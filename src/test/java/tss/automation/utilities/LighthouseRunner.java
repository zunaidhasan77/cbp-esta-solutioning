package tss.automation.utilities;

import java.nio.file.Path;
import jline.internal.Log;
import java.nio.file.Files;
import java.io.*;


public class LighthouseRunner {

    public static void runAudit(String url, String reportPath) {
        try {
            Log.info("Running Lighthouse audit for: " + url);

            // Ensure output directory exists
            Path outputDir = Path.of(reportPath).getParent();
            if (outputDir != null) {
                Files.createDirectories(outputDir);
            }

            // Build the Lighthouse process
            ProcessBuilder builder = new ProcessBuilder("lighthouse", url, "--quiet",
                    "--output=json", "--output=html", "--output-path=" + reportPath, // Lighthouse
                                                                                     // will use
                                                                                     // this path as
                                                                                     // a *prefix*
                    "--chrome-flags=--headless");

            builder.redirectErrorStream(true); // Merge stdout and stderr
            Process process = builder.start();

            // Safely capture output
            try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[Lighthouse] " + line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Lighthouse audit failed with exit code: " + exitCode);
            }

            Log.info("Lighthouse audit completed successfully.");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Lighthouse audit execution failed", e);
        }
    }
}
