package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        // Create a Spark Reporter instead of ExtentHtmlReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("API / UI Test Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void createTest(String name) {
        test = extent.createTest(name);
    }

    public static void flush() {
        extent.flush();
    }

    public static void logInfo(String msg) {
        if (test == null) createTest("API Test");
        test.info(msg);
    }

    public static void logPass(String msg) {
        if (test == null) createTest("API Test");
        test.pass(msg);
    }

    public static void logFail(String msg) {
        if (test == null) createTest("API Test");
        test.fail(msg);
    }
}
