package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private final static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private final static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"/reports/index.html");

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String className, String testName) {
        ExtentTest test = extent.createTest(className,testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
