package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;

public class ReportParser {

    //public static FileWriter writer= null;

    public static void main (String []args) {
        try{
        File input = new File(System.getProperty("user.dir")+"/reports/index.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements testCases = doc.select("div[class='test-detail']");
        FileWriter writer = new FileWriter("test-results-summary.txt");

        for (Element testCase : testCases) {
            String testName = testCase.select("div[class='test-detail']>p.name").text();
            String status = testCase.select("span.badge.pass-bg.log.float-right").text();
            writer.write("Test Case: " + testName + " - Status: " + status + "\n");
        }
        writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse report " + e.getMessage());
        }
        System.out.println("Test results summary written to test-results-summary.txt");
    }

}
