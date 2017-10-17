package com.example.at.hooks;


import com.example.at.config.webdriver.DriverBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("@web")
    public void setup(Scenario scenario) throws Exception {
        DriverBase.instantiateDriverObject();
        DriverBase.getDriver().manage().window().maximize();
        WebDriver driver = DriverBase.getDriver();

        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        logger.info("Starting Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
    }

    @After("@web")
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverBase.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        String sessionId = ((RemoteWebDriver) DriverBase.getDriver()).getSessionId().toString();
        logger.info("Ending Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
        DriverBase.closeDriverObjects();
    }
}
