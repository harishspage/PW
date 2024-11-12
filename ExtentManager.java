import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void setupExtentReport() {
        extent = new ExtentReports();
        // Initialize extent report configuration
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void log(String message) {
        test.log(Status.INFO, message);
    }

    public static void flushReports() {
        extent.flush();
    }
}
