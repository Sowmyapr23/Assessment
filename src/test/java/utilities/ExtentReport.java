package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The ExtentReport class is used for creating and generating an extent report
 */
public class ExtentReport {

    String sparkfileLocation = "./reports/cucumber-extent-reports/ExtentReport-" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".html";
    String extentReportImage = "./reports/cucumber-screenshots/";
    ExtentSparkReporter spark = new ExtentSparkReporter(sparkfileLocation);
    ExtentReports extent = new ExtentReports();
    ExtentTest extentTest = extent.createTest("TestStore Automation Test Result").assignAuthor("Tester").assignDevice("Windows");

    /**
     * The setSparkReport function is to create a new spark report folder
     * @throws IOException
     */
    public void setSparkReport() throws IOException {
        try {
            File file = new File(sparkfileLocation);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        extent.attachReporter(spark);
        spark.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        extent.setSystemInfo("Automation Tester", "Tester");
        extent.setSystemInfo("Organization", "TestStore");
        extent.setSystemInfo("Environment", "WEB");
        extent.setSystemInfo("OS", "Windows");
    }

    /**
     * The capture function is used to take screenshots in a specified format
     * @param driver
     * @return
     * @throws IOException
     */
    public String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(extentReportImage + System.currentTimeMillis() + ".png");
        String filePath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return filePath;
    }

    /**
     * The extentReportFormatter method is used to highlight the failed, skipped and passed status logs in Red, yellow and green respectively
     * Also attach the screenshots to the report
     * @param driver
     * @param stepDesc
     * @param result
     * @throws IOException
     */
    public void extentReportFormatter(WebDriver driver, String stepDesc, String result) throws IOException {
        if (result.equalsIgnoreCase("FAILURE")) {
            String failureLog = stepDesc;
            Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
            extentTest.log(Status.FAIL, m);
            extentTest.addScreenCaptureFromPath(capture(driver));

        } else if (result.equalsIgnoreCase("SKIP")) {
            String logText = stepDesc;
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            extentTest.log(Status.SKIP, m);
        } else if (result.equalsIgnoreCase("SUCCESS")) {
            String logText = stepDesc;
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentTest.log(Status.PASS, m);
            extentTest.addScreenCaptureFromPath(capture(driver));
        }
        extent.flush();
    }
}