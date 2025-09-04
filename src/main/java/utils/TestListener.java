package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (testReport.get() != null) {
            testReport.get().log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testReport.get();
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName() + " (auto-created)");
            testReport.set(test);
        }

        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Get WebDriver instance from BaseTest
        WebDriver driver = ((BaseTest) result.getInstance()).driver;

        // Take screenshot
        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (testReport.get() != null) {
            testReport.get().log(Status.SKIP, "Test Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // --------- Helper Method: Take Screenshot ---------
    private String takeScreenshot(WebDriver driver, String methodName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        String screenshotPath = screenshotDir + methodName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);

        try {
            dest.getParentFile().mkdirs(); // create directory if not exists
            Files.copy(src, dest);
            System.out.println("📸 Screenshot saved at: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest.getAbsolutePath(); // return absolute path for ExtentReports
    }
}
