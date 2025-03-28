package com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener;

import org.springframework.stereotype.Component;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class TestNgReportListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        Reporter.log("✅ TestNG Report Generated at: " + outputDirectory, true);
        StringBuilder report = new StringBuilder();
        report.append("<html><head><title>TestNG Custom Report</title>");
        report.append("<style>");
        report.append("body { font-family: Arial, sans-serif; margin: 20px; }");
        report.append("table { width: 100%; border-collapse: collapse; }");
        report.append("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        report.append("th { background-color: #4CAF50; color: white; }");
        report.append(".passed { background-color: #c6efce; }");
        report.append(".failed { background-color: #ffc7ce; }");
        report.append(".skipped { background-color: #ffeb9c; }");
        report.append("</style></head><body>");
        report.append("<h1>Test Execution Report</h1>");

        for (ISuite suite : suites) {
            report.append("<h2>Suite: ").append(suite.getName()).append("</h2>");

            // Get suite results
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();

                // Add summary
                report.append("<p><strong>Passed Tests:</strong> ").append(testContext.getPassedTests().size()).append("</p>");
                report.append("<p><strong>Failed Tests:</strong> ").append(testContext.getFailedTests().size()).append("</p>");
                report.append("<p><strong>Skipped Tests:</strong> ").append(testContext.getSkippedTests().size()).append("</p>");

                // Create test results table
                report.append("<table><tr><th>Test Name</th><th>Status</th><th>Execution Time</th><th>Screenshot</th></tr>");

                // Passed Tests
                addTestResults(report, testContext.getPassedTests().getAllResults(), "Passed", "passed");

                // Failed Tests (With Screenshot)
                addTestResults(report, testContext.getFailedTests().getAllResults(), "Failed", "failed");

                // Skipped Tests
                addTestResults(report, testContext.getSkippedTests().getAllResults(), "Skipped", "skipped");

                report.append("</table>");
            }
        }
        report.append("</body></html>");

        // Save report to file
        try {
            File reportFile = new File(outputDirectory + "/custom-report.html");
            FileWriter writer = new FileWriter(reportFile);
            writer.write(report.toString());
            writer.close();
            Reporter.log("✅ Custom TestNG Report Generated: " + reportFile.getAbsolutePath(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTestResults(StringBuilder report, java.util.Set<ITestResult> results, String status, String cssClass) {
        for (ITestResult result : results) {
            report.append("<tr class='").append(cssClass).append("'>");
            report.append("<td>").append(result.getName()).append("</td>");
            report.append("<td>").append(status).append("</td>");
            report.append("<td>").append((result.getEndMillis() - result.getStartMillis()) / 1000.0).append(" seconds</td>");

            // Add Screenshot for Failed Tests
            if (status.equals("Failed")) {
                String screenshotPath = "screenshots/" + result.getName() + ".png";
                File screenshotFile = new File(screenshotPath);
                if (screenshotFile.exists()) {
                    report.append("<td><a href='" + screenshotPath + "' target='_blank'>📸 View</a></td>");
                } else {
                    report.append("<td>No Screenshot</td>");
                }
            } else {
                report.append("<td>-</td>");
            }

            report.append("</tr>");
        }
    }
}
