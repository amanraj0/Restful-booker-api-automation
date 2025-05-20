package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportManager.ExtentReportManager;

import java.util.concurrent.ConcurrentHashMap;

public class TestsListeners implements ITestListener {
    private static ExtentReports extentReports;
    private static final ConcurrentHashMap<String, ExtentTest> classNodeMap = new ConcurrentHashMap<>();
    private static final ThreadLocal<ExtentTest> methodNode = new ThreadLocal<>();

    public static ExtentTest getCurrentTest() {
        return methodNode.get();
    }
    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReportManager.createInstance("Reports");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        // Create or retrieve the parent node (one per class)
        ExtentTest classNode = classNodeMap.computeIfAbsent(className,
                name -> extentReports.createTest("Endpoint: " + name));

        // Create a child node under the class
        ExtentTest testNode = classNode.createNode("Test Case: " + result.getMethod().getDescription());
        testNode.log(Status.INFO, "Starting execution of: " + methodName);
        testNode.log(Status.INFO, "Description: " + result.getMethod().getDescription());

        methodNode.set(testNode);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        methodNode.get().log(Status.PASS, "Test passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        methodNode.get().log(Status.FAIL, "Test failed.");
        methodNode.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        methodNode.get().log(Status.SKIP, "Test skipped.");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
