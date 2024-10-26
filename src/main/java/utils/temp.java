package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class temp {

    public static void parseHTMLReporttemp(String filePath) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border='1'><tr><th>Test Case Name</th><th>Test Case Status</th></tr>");

        int totalTestCasesCount = 0;
        int passedTestCasesCount = 0;
        int failedTestCasesCount = 0;

        try {
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");
            Elements testCases = doc.select("div.test-detail");

            // Count total test cases
            totalTestCasesCount = testCases.size();

            try (FileWriter writer = new FileWriter("reports/test-results-summary.txt")) {
                // Loop through each test case
                for (Element testCase : testCases) {
                    String testName = testCase.select("p.name").text();
                    String status = testCase.select("span.badge.pass-bg.log.float-right").text();

                    // Append the test case details to the table
                    builder.append("<tr><td>").append(testName).append("</td>");

                    if (status.equalsIgnoreCase("Pass")) {
                        passedTestCasesCount++;
                        writer.write("Test Case: " + testName + " - Status: " + status + "\n");
                        builder.append("<td>").append(status).append("</td>");
                    } else {
                        failedTestCasesCount++;
                        writer.write("Test Case: " + testName + " - Status: Fail\n");
                        builder.append("<td>Fail</td>");
                    }
                    builder.append("</tr>");

                    // Select and iterate through the test steps (if needed)
                    Elements steps = testCase.select("table.table.table-sm tr");
                    for (Element step : steps) {
                        String stepStatus = step.select("td:nth-child(1)").text();
                        String stepDetails = step.select("td:nth-child(3)").text();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to parse report: " + e.getMessage());
        }

        // Close the table
        builder.append("</table>");

        // Append test case count summary at the end of the report
        builder.append("<br><strong>Total Tests:</strong> ").append(totalTestCasesCount);
        builder.append(", <strong>Passed Tests:</strong> ").append(passedTestCasesCount);
        builder.append(", <strong>Failed Tests:</strong> ").append(failedTestCasesCount);

        // Write the summary report
        try {
            writeTestResults(builder.toString());
        } catch (IOException e) {
            System.err.println("Unable to write summary report: " + e.getMessage());
        }
    }

    private static void writeTestResults(String summaryReportDetails) throws IOException {

        try (FileWriter writer = new FileWriter("reports/summaryReport.html")) {
            writer.write(summaryReportDetails);
        } catch (IOException e) {
            System.err.println("Unable to write summary details: " + e.getMessage());
        }
    }
}
