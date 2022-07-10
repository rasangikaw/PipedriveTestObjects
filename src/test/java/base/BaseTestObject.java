package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Config;

import java.util.logging.Logger;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTestObject {

    public static WebDriver driver;
    public Logger logger = Logger.getLogger(BaseTestObject.class.getName());

    private File file;
    protected Map<String, JSONObject> value;
    private JSONParser parser;
    private String testDataPath;
    public String pipedriveUrl;

    @BeforeSuite(alwaysRun = true)
    public void startUp() throws Exception {
        try {

            Config.setConfigFilePath("/config/config.properties");
            testDataPath = Config.getProperty("template.data.path");
            pipedriveUrl = Config.getProperty("pipedrive.url");
            parser = new JSONParser();

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } catch (Exception e) {
            throw e;
        }
    }

    public JSONObject getJSONTestData(String dataName) throws Exception {

        try {
            file = new File(testDataPath);
            value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
            return value.get(dataName);

        } catch (Exception e) {
            throw e;
        }
    }

    public JSONObject getNestedJSONTestData(String dataName, String nestedDataName) throws Exception {

        try {
            file = new File(testDataPath);
            value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
            JSONObject nestedJson = (JSONObject) value.get(dataName);
            return (JSONObject) nestedJson.get(nestedDataName);

        } catch (Exception e) {
            throw e;
        }
    }

    public JSONObject getNestedJSONTestData(String dataName, String firstNestedDataName, String secondNesteddataName) throws Exception {

        try {
            file = new File(testDataPath);
            value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
            JSONObject nestedJson = (JSONObject) value.get(dataName);
            JSONObject firstNestedJson = (JSONObject) nestedJson.get(firstNestedDataName);
            return (JSONObject) firstNestedJson.get(secondNesteddataName);

        } catch (Exception e) {
            throw e;
        }
    }

    public JSONObject getNestedJSONTestData(String dataName, String firstNestedDataName, String secondNestedDataName, String thirdNestedDataName) throws Exception {

        try {
            file = new File(testDataPath);
            value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
            JSONObject nestedJson = (JSONObject) value.get(dataName);
            JSONObject firstNestedJson = (JSONObject) nestedJson.get(firstNestedDataName);
            JSONObject secondNestedJson = (JSONObject) firstNestedJson.get(secondNestedDataName);
            return (JSONObject) secondNestedJson.get(thirdNestedDataName);

        } catch (Exception e) {
            throw e;
        }
    }

    @AfterSuite
    public void tearDown() throws Exception {

        try {
            if (driver != null) driver.quit();
        } catch (Exception e) {
            throw e;
        }
    }
}
