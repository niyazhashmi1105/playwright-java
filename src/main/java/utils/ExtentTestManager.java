package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public final class ExtentTestManager {

    private ExtentTestManager(){}
    private final static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private final static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"/reports/index.html");


    /**
     * Retrieves the {@link ExtentTest} instance associated with the current thread.
     * This method is designed to fetch the current thread's test context from the
     * {@code extentTestMap}. It is synchronized to ensure thread safety when
     * multiple threads are accessing the test context simultaneously.
     *
     * <p>
     * The method works by using the current thread's ID as a key to look up the
     * corresponding {@code ExtentTest} instance in the {@code extentTestMap}.
     * This allows for storing and retrieving test execution information in a
     * thread-safe manner, which is especially important in a parallel testing
     * environment.
     * </p>
     *
     * @return the {@link ExtentTest} instance associated with the current thread,
     *         or {@code null} if no test instance exists for the current thread.
     * @throws NullPointerException if the thread ID is not found in the map,
     *         indicating that no test instance has been started for this thread.
     */
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /**Finalizes and flushes the current test context to the ExtentReports output.
     * This method is responsible for ensuring that all logs, steps, and
     * information related to the current test execution are written to
     * the ExtentReports output file. It is synchronized to maintain thread
     * safety when invoked from multiple threads concurrently.
     *
     * <p>
     * After calling this method, the report will contain all the details
     * related to the test that was executed in the current thread, including
     * any logs, screenshots, and other information captured during the test.
     * This method should be called at the end of each test execution to ensure
     * that the report is up-to-date and reflects the latest test results.
     * </p>
     *
     * @throws NullPointerException if the {@link ExtentReports} instance is
     *         not initialized or is null, indicating that the reporting
     *         mechanism is not set up correctly.
     */
    public static synchronized void endTest() {
        extent.flush();
    }

    /**
     * Initiates a new test instance in the ExtentReports framework and associates
     * it with the current thread.
     * This method creates a new {@link ExtentTest} instance using the provided
     * class name and test name. The test instance is stored in a thread-safe
     * manner, allowing for concurrent test executions to be reported correctly.
     * <p>
     * The created test instance can be used to log steps, attach screenshots,
     * and capture other relevant information during the test execution. Each
     * test is uniquely identified by the combination of the class name and test
     * name, making it easy to organize and analyze test results in the generated
     * reports.
     * </p>
     *
     * @param className the name of the test class associated with this test.
     *                  This provides context to the test report and helps
     *                  in identifying the source of the test.
     * @param testName   the name of the individual test case being executed.
     *                   This name will be displayed in the test report,
     *                   allowing for clear differentiation between multiple
     *                   test cases.
     * @return the created {@link ExtentTest} instance, which can be used to
     *         log details about the test execution.
     *
     * @throws NullPointerException if the {@link ExtentReports} instance is
     *         not initialized or is null, indicating that the reporting
     *         mechanism is not set up correctly.
     */
    public static synchronized ExtentTest startTest(String className, String testName) {
        ExtentTest test = extent.createTest(className,testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
