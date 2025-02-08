package api.utils.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener  implements ITestListener {
    public static String workingDir = System.getProperty("user.dir");
    private final ExtentReports extentReports;
    private final ExtentTest extentTest;
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter(workingDir + "/ExtentReports/ExtentReportResults.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentTest = extentReports.createTest("MyTestListener");
    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("---------------------------------------------------");
        System.out.println("Method " + result.getName() + " Start");
        extentTest.pass("Test started " + result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test Success " + result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Method " + result.getName() + " Failed");
        extentTest.fail("Test Failed: " + result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Method " + result.getName() + " Skipped");
        extentTest.skip("Test Skipped " + result.getName());
        extentReports.flush();
    }

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Test "+context.getName()+" Start");

        extentTest.info("Start Test");
        extentReports.flush();
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test "+context.getName()+" End");

        extentTest.info("Finished");
        extentReports.flush();
    }
}
