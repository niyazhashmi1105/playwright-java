package listeners;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentTestManager;
import utils.ReportParser;

import java.awt.*;
import java.io.File;

public class ReportListener implements ITestListener, IExecutionListener {

    private final static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    /**
     * Retrieves the current {@link Page} instance from the thread-local storage.
     *
     * This method is used to access the {@link Page} object that is stored in a {@link ThreadLocal} variable.
     * The {@link Page} object represents the browser page or tab in the Playwright context, which is used for browser automation.
     *
     * @return The {@link Page} instance associated with the current thread.
     *         If the thread-local variable is not initialized, this method will return {@code null}.
     *
     * @see Page
     * @see ThreadLocal
     */

    public static Page getPage(){
        return tlPage.get();
    }

    /**
     * Sets the {@link Page} instance for the current thread in the thread-local storage.
     *
     * This method assigns the provided {@link Page} object to the thread-local variable {@code tlPage},
     * which allows each thread to store its own {@link Page} instance separately.
     *
     * @param page The {@link Page} object to be stored in the thread-local variable for the current thread.
     *
     * @see Page
     * @see ThreadLocal
     */

    public static void setPage(Page page){
        tlPage.set(getPage());
    }

    @Override
    public void onExecutionStart(){
        System.out.println("Test Automation Suites starting...");
    }

    /**
     * Called when the execution of the test automation suite starts.
     *
     * This method is typically overridden to provide custom behavior at the beginning of test execution.
     * It is invoked before any test cases are run, and can be used to perform setup tasks such as logging or
     * initializing resources for the test suite.
     *
     * In this implementation, it simply prints a message indicating that the test automation suite is starting.
     *
     * @see #onExecutionFinish()
     */
    @Override
    public void onExecutionFinish(){

        ReportParser.parseHTMLReport(System.getProperty("user.dir")+"/reports/index.html");
        System.out.println("Test Automation Suites ended...");
    }

    /**
     * This method is invoked each time a test method starts execution.
     *
     * It is typically overridden to provide custom behavior when an individual test case starts.
     * This implementation logs the test name, initiates a new test entry in the Extent Reports,
     * attaches initial logs, and adds an informational log entry to the report.
     *
     * @param result The {@link ITestResult} object containing details of the test method that is starting,
     *               such as the test class name and method name.
     *
     * @see #onTestSuccess(ITestResult)
     * @see #onTestFailure(ITestResult)
     */
    @Override
    public void onTestStart(ITestResult result) {

        System.out.println(result.getMethod().getMethodName() + " test starting.");
        ExtentTestManager.startTest(result.getTestClass().getName(),result.getMethod().getMethodName());
        attachLogs("Test started: "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.INFO, result.getMethod().getMethodName()+" test execution started");
    }

    /**
     * This method is invoked when a test method completes successfully.
     *
     * It is typically overridden to log and report the successful execution of a test.
     * This implementation logs the test name to the console, attaches success logs,
     * and marks the test as passed in the Extent Report with the PASS status.
     *
     * @param result The {@link ITestResult} object containing details of the test method that succeeded,
     *               such as the test method name and class.
     *
     * @see #onTestFailure(ITestResult)
     * @see #onTestSkipped(ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " test passed.");
        attachLogs("Test passed: "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName()+ " test execution passed.");
    }

    /**
     * This method is invoked when a test method fails during execution.
     *
     * It logs the failure of the test, captures a screenshot (if applicable), and attaches the screenshot along with the stack trace to the Extent Report.
     * This provides detailed failure information for reporting and debugging purposes.
     *
     * @param result The {@link ITestResult} object containing details of the failed test method,
     *               such as the test method name, class, and the exception thrown.
     *
     * @see #onTestSuccess(ITestResult)
     * @see #onTestSkipped(ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest baseTest) {
            String base64Screenshot = baseTest.testUtil.captureScreenshotAsBase64();
            // Log failure and attach screenshot
            ExtentTestManager.getTest().fail(result.getName()+" test execution failed with "+ ExceptionUtil.getStackTrace(result.getThrowable()),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
    }

    /**
     * This method is invoked when a test method is skipped during execution.
     *
     * It logs the skip event and marks the test as skipped in the Extent Report.
     * This allows for proper documentation of tests that were skipped due to various reasons, such as unmet preconditions.
     *
     * @param result The {@link ITestResult} object containing details of the skipped test method,
     *               such as the test method name and class.
     *
     * @see #onTestSuccess(ITestResult)
     * @see #onTestFailure(ITestResult)
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName() + " test execution skipped.");
        attachLogs("Test skipped:"+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, result.getName()+" test execution skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    /**
     * This method is invoked after all the test methods have been executed in the test context.
     *
     * It performs cleanup operations such as logging the completion of all tests and
     * finalizing the Extent Report. Additionally, it opens the generated Extent Reports for viewing.
     *
     * @param context The {@link ITestContext} object that contains information about the
     *                test run, including the suite, the results, and other metadata.
     *
     * @see #onTestStart(ITestResult)
     * @see #onTestSuccess(ITestResult)
     * @see #onTestFailure(ITestResult)
     * @see #onTestSkipped(ITestResult)
     */
    @Override
    public void onFinish(ITestContext context) {
        attachLogs("All tests finished:");
        ExtentTestManager.endTest();
        openExtentReport("reports/index.html");
        openExtentReport("reports/summaryReport.html");
}

    /**
     * Captures a screenshot of the current page and attaches it to the test report.
     *
     * This method retrieves the current {@link Page} instance and takes a screenshot of the entire page.
     * If the screenshot is successfully captured, it is returned as a byte array and
     * attached to the report with the specified name. In case of failure, logs are generated
     * to indicate the issue.
     *
     * @param screenshotName The name to be assigned to the screenshot in the report.
     *                       This can be useful for identifying specific screenshots in the reports.
     * @return A byte array representing the screenshot in PNG format. If the page object is null
     *         or an exception occurs, an empty byte array is returned.
     *
     * @throws RuntimeException If there is an error capturing the screenshot or if the page object is null.
     *
     * @see Page#screenshot(Page.ScreenshotOptions)
     * @see #attachLogs(String)
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] captureScreenshot(String screenshotName) {
        try {
            Page page = getPage();
            if (page != null) {
                return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            } else {
                attachLogs("Page object is null, unable to capture screenshot.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Please check the exception stack: "+e.getMessage());
        }
        return new byte[0];
    }

    @Step("{0}")
    public String attachLogs(String message) {
        return message;  // Log the message to Allure report
    }

    /**
     * Opens the specified Extent report file in the default web browser.
     *
     * This method attempts to open the report located at the given file path. If the file exists,
     * it will be opened using the system's default web browser. If the file does not exist or if
     * there is an error during the operation, appropriate messages will be printed to the console.
     *
     * @param filePath The file path to the Extent report that needs to be opened. This should
     *                 include the full path to the report file.
     *
     * @throws IOException If an error occurs while attempting to open the file (e.g., if the
     *                     operating system does not support the operation).
     */
    private static void openExtentReport(String filePath) {
        try {
            File reportFile = new File(filePath);
            if (reportFile.exists()) {
                Desktop.getDesktop().open(reportFile); // Opens the report with default browser
            } else {
                System.out.println("Report file does not exist.");
            }
        } catch (Exception e) {
            System.err.println("Unable to open report: " + e.getMessage());
        }
    }
}