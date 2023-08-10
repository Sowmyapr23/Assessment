package utilities;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * The CommonMethods Class serves as a utility class which consists
 * of set of methods which performs common and often re-used functions
 */
public class CommonMethods {

    private WebDriver driver;
    private final Properties locatorfile = new Properties();
    private final Properties configfile = new Properties();
    ExtentReport extentReportGeneration = new ExtentReport();
    Properties locatorFile = null;
    Properties configFile = null;
    private String site_url;
    final int time=20;


    /**
     * The loadLocatorFile function is used to load the locator property file
     * @throws IOException
     * @return locatorfile
     */
    public Properties loadLocatorFile() throws IOException {
        String locatorFile = "./src/test/resources/locators.properties";
        FileInputStream file = new FileInputStream((locatorFile));
        locatorfile.load(file);
        return locatorfile;
    }

    /**
     * The loadConfigFile function is used to load the configuration property file
     * @throws IOException
     * @return configfile
     */
    public Properties loadConfigFile() throws IOException {
        String configFile = "./src/test/resources/config.properties";
        FileInputStream file = new FileInputStream((configFile));
        configfile.load(file);
        return configfile;
    }

    /**
     * The extentReportFormatter function is to generate report
     * @param stepDesc
     * @param result
     * @throws IOException
     */
    public void extentReportFormatter(String stepDesc, String result) throws IOException {
        extentReportGeneration.setSparkReport();
        extentReportGeneration.extentReportFormatter(driver, stepDesc, result);
    }

    /**
     * This identifyElement function is used to read the locator type and value
     * @param locators
     * @return element
     */
    public WebElement identifyElement(String locators) {
        WebElement element = null;
        String objectIdentifier = StringUtils.substringBefore(locators, "~");
        String objectLocator = StringUtils.substringAfter(locators, "~");
        if ("xpath".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.xpath(objectLocator));
        } else if ("id".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.id(objectLocator));
        } else if ("classname".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.className(objectLocator));
        } else if ("name".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.name(objectLocator));
        } else if ("linktext".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.linkText(objectLocator));
        } else if ("partiallinktext".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.partialLinkText(objectLocator));
        } else if ("tagname".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.tagName(objectLocator));
        } else if ("cssselector".equalsIgnoreCase(objectIdentifier)) {
            element = driver.findElement(By.cssSelector(objectLocator));
        } else {
            System.out.println("Provided object locator might be not valid");
        }
        return element;
    }

    /**
     * The waitForElementToBeClickable function is used to make the driver
     * explicitly wait until the web element is clickable
     * @param element
     */
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * The waitForElementToBeVisible function is used to make the driver
     * explicitly wait until the web element is visible
     * @param element
     */
    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * The initiateDriver function is used for desired driver initialisation
     * @param browser
     * @return
     * @throws IOException
     */
    public WebDriver initiateDriver(String browser) throws IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
            driver=new EdgeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * The launchBrowser function is used to launch the application in web browser
     * @throws IOException
     */
    public void launchBrowser() throws IOException {
        try {
            this.locatorFile = loadLocatorFile();
            this.configFile = loadConfigFile();

            String browserName = configFile.getProperty("browser_name");
            this.driver = initiateDriver(browserName);

            site_url = configFile.getProperty("site_url");
            driver.get(site_url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
            extentReportFormatter("Application Launched successfully", "SUCCESS");
        } catch (IOException e) {
            extentReportFormatter("Application Launch failed", "FAILURE");
        }
    }

    /**
     * The clickOnElement function is used to validate if the web element
     * is present/displayed in the webpage and perform click action on it
     * @param
     * @throws IOException
     */
    public void clickOnElement(String elementId) throws IOException {
        WebElement ele = identifyElement(locatorFile.getProperty(elementId));
        boolean isElementPresent=ele.isDisplayed();
        try {
            if (isElementPresent) {
                waitForElementToBeClickable(ele);
                ele.click();
            }
            extentReportFormatter("Clicked on " + elementId  , "SUCCESS");
        } catch (Exception e) {
            extentReportFormatter("Not clicked on " + elementId + "</br><b>Info Log: </b>" + e.toString(), "FAILURE");
     }
    }

    /**
     * The enterInTextBox function is used to enter a text in a textBox
     * @param inputData
     * @param elementId
     * @throws IOException
     */
        public void enterInTextBox(String inputData, String elementId) throws IOException {
                WebElement element = identifyElement(locatorFile.getProperty(elementId));
                waitForElementToBeClickable(element);
                try
                {
                    if(element.getText()!=null) {
                        element.clear();
                        element.sendKeys(inputData);
                    }
                    else
                    {
                        element.sendKeys(inputData);
                    }
                    extentReportFormatter("Entered " + inputData + " in " + elementId, "SUCCESS");

            } catch (Exception e) {
                extentReportFormatter("Data not entered in " + elementId + "/n Info Log: " + e.toString(), "FAILURE");
            }
    }

    /**
     * The selectFromDropdown function is used to select a value from the drop-down list
     * @param inputData
     * @param elementId
     * @throws IOException
     */
    public void selectFromDropdown(String inputData,String elementId) throws IOException {
        try {
            WebElement element = identifyElement(locatorFile.getProperty(elementId));
            boolean isElementAvailable=element.isDisplayed();
            if(isElementAvailable)
            {
                waitForElementToBeClickable(element);
                Select dropdown=new Select(element);
                dropdown.selectByVisibleText(inputData);
            }
            extentReportFormatter("Selected " + inputData + "from " + elementId, "SUCCESS");
            }
        catch(Exception e)
        {
            extentReportFormatter(inputData+ " not selected from " + elementId + "/n Info Log: " + e.toString(), "FAILURE");
        }
    }

    /**
     * The compareTexts function is used to validate one string against another
     * @param expectedInput
     * @param elementId
     * @throws IOException
     */
    public void compareTexts(String expectedInput, String elementId) throws IOException {
        try {
            WebElement element = identifyElement(locatorFile.getProperty(elementId));
            waitForElementToBeVisible(element);
            String actualText = element.getText().toLowerCase().trim();
            if(actualText.equalsIgnoreCase(expectedInput.trim()))
            {
                extentReportFormatter("The expected Text " + expectedInput + " is equals to actual text " + actualText, "SUCCESS");
            }
        } catch (Exception e) {
            extentReportFormatter("Assertion failed " + e.toString(), "FAILURE");
        }
    }

    /**
     * The compareTexts function is used to assert a part of one string with another string
     * @param expectedText
     * @param elementId
     * @param n
     * @throws IOException
     */
    public void compareTexts(String expectedText, String elementId, int n) throws IOException {
        try {
        WebElement element = identifyElement(locatorFile.getProperty(elementId));
        waitForElementToBeVisible(element);
        String[] actualText = element.getText().split(" ");
        Assert.assertEquals("Equals", actualText[n], expectedText);
        extentReportFormatter("Asserting expected text " + expectedText + " equals to actual text " + actualText[2], "SUCCESS");
    } catch (Exception e) {
        extentReportFormatter("Assertion failed " + e.toString(), "FAILURE");
    }
    }

    /**
     * The closeApp function is used to close all browser windows and end the driver session
     * @throws IOException
     */
    public void closeApp() throws IOException {
        try {
            extentReportFormatter("Application is closed successfully", "SUCCESS");
            driver.quit();
        } catch (Exception e) {
            extentReportFormatter("Application is Not closed" + "/n Info Log: " + e.toString(), "FAILURE");

        }
    }
    }