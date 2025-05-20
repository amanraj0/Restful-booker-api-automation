package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.filter.Filter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportManager.ExtentReportManager;

public class TestListeners implements ITestListener {

    private static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public void onStart(ITestContext context) {
        String filename = "Reports";
        extentReports = ExtentReportManager.createInstance(filename);
    }

    public void onTestStart(ITestResult result) {
        extentTest.log(Status.INFO,"Execution started for "+result.getName());
        extentTest.log(Status.INFO,"Test Case: "+result.getMethod().getDescription());

    }

    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,"Execution Done!!");
    }

    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL,"Execution Done!!");
    }

    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Execution Skipped!!");
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
