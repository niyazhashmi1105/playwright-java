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

    private ExtentManager() {
    }

    private static ExtentReports extent;

    /**
     * Creates an instance of {@code ExtentReports} with the specified
     * report file name. Configures the report settings and system information.
     *
     * @param fileName the name of the file where the report will be generated.
     * @return an instance of {@code ExtentReports} configured with the
     * specified report name, document title, and system information.
     */
    public static ExtentReports createInstance(String fileName) {

        if (extent == null) {
            synchronized (ExtentManager.class) {
                // Configure the report location and file
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
                sparkReporter.config().setReportName("Playwright Automation Test Report");
                sparkReporter.config().setDocumentTitle("Extent Report");
                sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

                //sparkReporter.setAppendExisting(true);
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);

                extent.setSystemInfo("Environment", ConfigReader.getEnvironment());
                extent.setSystemInfo("URL", ConfigReader.getURL());
                extent.setSystemInfo("OS Name", System.getProperty("os.name"));
                extent.setSystemInfo("OS Version", System.getProperty("os.version"));
                extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
                extent.setSystemInfo("Browser", ConfigReader.getBrowser());
                extent.setSystemInfo("Browser Version", ConfigReader.getBrowserVersion());
                extent.setSystemInfo("Headless", String.valueOf((Boolean)ConfigReader.isHeadless()));

            }
        }
        return extent;
    }

}

