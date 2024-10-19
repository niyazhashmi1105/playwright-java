package listeners;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentTestManager;

public class ReportListener implements ITestListener{

    private final static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Page getPage(){
        return tlPage.get();
    }
    public static void setPage(Page page){
        tlPage.set(getPage());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " test starting.");
        ExtentTestManager.startTest(result.getMethod().getMethodName());
        attachLogs("Test started: "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.INFO, result.getMethod().getMethodName()+" test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " test passed.");
        attachLogs("Test passed: "+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName()+ " test execution passed.");
    }

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

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
        System.out.println(result.getName() + " test execution skipped.");
        attachLogs("Test skipped:"+result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, result.getName()+" test execution skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        attachLogs("Test failed with success percentage: "+result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // Called when the test suite starts
        attachLogs("All tests starting:");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Called when the test suite finishes
        attachLogs("All tests finished:");
        ExtentTestManager.endTest();
}
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
}