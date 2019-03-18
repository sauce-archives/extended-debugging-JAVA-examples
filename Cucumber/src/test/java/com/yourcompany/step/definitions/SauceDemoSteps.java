package com.yourcompany.step.definitions;

import com.yourcompany.utils.SauceUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.util.UUID;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.List;


import com.yourcompany.Pages.*;

import static org.hamcrest.CoreMatchers.containsString;

public class SauceDemoSteps {

    public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static WebDriver driver;
    public static SauceDemoPage page;
    public Map<String, Object> performance;
    public Map<String, Object> metrics;
    public Map<String, Object> performanceOutput;
    public Map<String, Object> requestsOutput;
    List<Map<String, Object>> network;
    Map<String, Object> timingLog;
    public String sessionId;
    public Scenario scenarioRef;

    @Before
    public void setUp(Scenario scenario) throws Exception {
        String platformProperty = System.getProperty("platform");

		String platform = (platformProperty != null) ? platformProperty : "windows_10_edge";

        DesiredCapabilities caps = SauceUtils.createCapabilities(platform);

        scenarioRef = scenario;
        caps.setCapability("name", scenario.getName());
        caps.setCapability("build", SauceUtils.getBuildName());
        caps.setCapability("extendedDebugging", true);

        driver = new RemoteWebDriver(new URL(URL), caps);

        sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
    }

    @Given("^I am on the SauceDemo homepage$")
    public void user_is_on_home_page() throws Exception {
        page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
    }


    @When("^I fetch performanceLog$")
    public void user_fetch_performace_logs() throws Exception {
        performance = page.getPerformance();
    }

    @Then("^All the metrics should exists$")
    public void metrics_should_exists() throws Exception {
        assertTrue(performance.containsKey("load"));
        assertTrue(performance.containsKey("speedIndex"));
        assertTrue(performance.containsKey("pageWeight"));
        assertTrue(performance.containsKey("pageWeightEncoded"));
        assertTrue(performance.containsKey("timeToFirstByte"));
        assertTrue(performance.containsKey("timeToFirstInteractive"));
        assertTrue(performance.containsKey("firstContentfulPaint"));
        assertTrue(performance.containsKey("perceptualSpeedIndex"));
        assertTrue(performance.containsKey("domContentLoaded"));
    }

    @When("^I execute sauce:performance on page load$")
    public void user_execute_performance() throws Exception {
        performanceOutput = page.assertPerformancePageLoad(scenarioRef.getName());
    }

    @Then("^sauce:performance should assert performance is okay$")
    public void performance_should_assert_performance() throws Exception {
        String performanceResult = performanceOutput.get("result").toString();
        assertEquals(performanceResult, "pass");
    }

    @When("^I execute sauce:performance with on firstByte$")
    public void user_execute_performance_requests() throws Exception {
        requestsOutput = page.assertPerformancefirstByte(scenarioRef.getName());
    }

    @Then("^sauce:performance should assert firstByte is okay$")
    public void performance_should_assert_performance_requests() throws Exception {
        String performanceResult = requestsOutput.get("result").toString();
        assertEquals(performanceResult, "pass");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        driver.quit();
        SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId);
    }
}
