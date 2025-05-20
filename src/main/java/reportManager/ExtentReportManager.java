package reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentReports extentReports;
    public static ExtentReports createInstance(String filename){

        ExtentSparkReporter extentSparkReporter =
                new ExtentSparkReporter(filename);
        extentSparkReporter.config().setReportName("Restful booker API Automation Report");
        extentSparkReporter.config().setDocumentTitle("Restful booker API");
        extentSparkReporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }
}
