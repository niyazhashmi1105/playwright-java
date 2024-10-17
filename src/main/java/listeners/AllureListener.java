package listeners;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    private final static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();

    public static void setPage(Page page){
        threadLocalPage.set(page);
    }

    public static Page getPage() {
        return threadLocalPage.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Log test start
        System.out.println(result.getName() + " test started.");
        attachLogs("Test started: "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success
        System.out.println(result.getName() + " test passed.");
        attachLogs("Test passed: "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure
        System.out.println(result.getName() + " test failed.");
        captureScreenshot(result.getMethod().getMethodName());
        attachLogs("Test failed: "+result.getMethod().getMethodName());


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
        System.out.println(result.getName() + " test skipped.");
        attachLogs("Test skipped:"+result.getMethod().getMethodName());
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
        if (getPage() != null) {
            getPage().close();
            threadLocalPage.remove();
        }
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
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Step("{0}")
    public String attachLogs(String message) {
        return message;  // Log the message to Allure report
    }
}
