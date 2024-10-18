package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    // Method to create an ExtentReport instance
    public static ExtentReports createInstance(String fileName) {

        // Configure the report location and file
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Extent Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System information to be included in the report
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "MD. Niyaz Hashmi");
        extent.setSystemInfo("OS Name",System.getProperty("os.name"));
        extent.setSystemInfo("OS Version",System.getProperty("os.version"));
        extent.setSystemInfo("Browser",ConfigReader.getProperty("browser"));
        return extent;
    }

    // Getter for ExtentReports instance
    public static ExtentReports getExtent() {
        return extent;
    }
}
