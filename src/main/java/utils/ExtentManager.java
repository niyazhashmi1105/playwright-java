package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import configurator.ConfigReader;

/**
 * The {@code ExtentReportManager} class is responsible for managing
 * the creation and configuration of ExtentReports instances.
 * It provides methods to initialize the report settings and retrieve
 * the current report instance.
 */
public final class ExtentManager {

    private ExtentManager(){}
    /**
     * Creates an instance of {@code ExtentReports} with the specified
     * report file name. Configures the report settings and system information.
     *
     * @param fileName the name of the file where the report will be generated.
     * @return an instance of {@code ExtentReports} configured with the
     *         specified report name, document title, and system information.
     */
    public static ExtentReports createInstance(String fileName) {

        // Configure the report location and file
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Extent Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "MD.Niyaz Hashmi");
        extent.setSystemInfo("OS Name",System.getProperty("os.name"));
        extent.setSystemInfo("OS Version",System.getProperty("os.version"));
        extent.setSystemInfo("Browser", ConfigReader.getBrowser());
        return extent;
    }
}
