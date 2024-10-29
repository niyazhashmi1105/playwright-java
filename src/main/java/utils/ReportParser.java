package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code ReportParser} class provides functionality to parse
 * an HTML test report and generate a summary report in HTML format
 * and a text file with the results of individual test cases.
 */
public final class ReportParser {

    private ReportParser(){}
    /**
     * Parses the HTML report located at the specified file path
     * and generates a summary report containing the total,
     * passed, and failed test cases.
     *
     * @param filePath the path to the HTML report file to be parsed.
     */
    public static void parseHTMLReport(String filePath) {
        StringBuilder builder = new StringBuilder();

        builder.append(" <h1> Test Results Summary </h1> ");
        builder.append(" <br> ");
        builder.append(" <table border='1'><tr><th> Test Case Name </th><th> Test Case Status </th></tr> ");

        int totalTestCasesCount = 0;
        int passedTestCasesCount = 0;
        int failedTestCasesCount = 0;
        int skippedTestCasesCount = 0;

        try {
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");
            Elements testCases = doc.select("div.test-detail");

            // Count total test cases
            totalTestCasesCount = testCases.size();

            // Using try-with-resources to ensure writer is closed automatically
            try (FileWriter writer = new FileWriter("reports/test-results-summary.txt")) {
                // Loop through each test case
                for (Element testCase : testCases) {
                    String testName = testCase.select("p.name").text();
                    String status = testCase.select("span.badge").text();

                    // Append the test case details to the table
                    builder.append("<tr><td>").append(testName).append("</td>");

                    if (status.equalsIgnoreCase("Pass")) {
                        passedTestCasesCount++;
                        writer.write(" Test Case: " + testName + " - Status: " + status + "\n");
                        builder.append("<td>").append(status).append("</td>");
                    } else if(status.equalsIgnoreCase("Fail")) {
                        failedTestCasesCount++;
                        writer.write(" Test Case: " + testName + " - Status: Fail\n");
                        builder.append("<td>Fail</td>");
                    }
                    else if(status.equalsIgnoreCase("Skip")){
                        skippedTestCasesCount++;
                        writer.write(" Test Case: " + testName + " - Status: Skip\n");
                        builder.append("<td>Skip</td>");
                    }
                    builder.append("</tr>");
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to parse report: " + e.getMessage());
        }

        // Close the table
        builder.append("</table>");

        // Update summary with actual counts at the top
        String summary = "<br> <strong> Total Tests: </strong> " + totalTestCasesCount +
                ", <strong> Passed Tests:</strong> " + passedTestCasesCount +
                ", <strong> Failed Tests:</strong> " + failedTestCasesCount+
                ", <strong> Skipped Tests:</strong> " + skippedTestCasesCount;
        builder.insert(builder.indexOf("<br>") + 4, summary);

        // Write the summary report
        try {
            writeTestResults(builder.toString());
        } catch (IOException e) {
            System.err.println("Unable to write summary report: " + e.getMessage());
        }
    }

    /**
     * Writes the summary report details to an HTML file.
     *
     * @param summaryReportDetails the summary report details to be written.
     * @throws IOException if an error occurs while writing to the file.
     */
    private static void writeTestResults(String summaryReportDetails) throws IOException {

        try (FileWriter writer = new FileWriter("reports/summaryReport.html")) {
            writer.write(summaryReportDetails);
        } catch (IOException e) {
            System.err.println("Unable to write summary details: " + e.getMessage());
        }
    }
}