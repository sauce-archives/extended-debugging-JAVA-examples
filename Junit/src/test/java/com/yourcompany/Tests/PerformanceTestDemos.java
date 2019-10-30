package com.yourcompany.Tests;

import com.google.common.collect.Maps;
import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static org.junit.Assert.*;

public class PerformanceTestDemos extends TestBase {

    public PerformanceTestDemos(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }
    @Test
    public void simplePerformanceTest() throws InvalidElementStateException {
        String methodName = name.getMethodName();
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        Map loadTime = page.getPageLoad(methodName);
        String pageLoadResult = loadTime.get("result").toString();
        assertEquals(pageLoadResult, "pass");
        Map performanceMetrics = page.getPerformance();
        long maxAllowedSpeedIndex = 1000;
        long currentSpeedIndex = (long) performanceMetrics.get("speedIndex");
        assertTrue("Exceeded maximum allowed speed index", maxAllowedSpeedIndex >= currentSpeedIndex);
    }
    @Test
    public void verifyPerformanceFlowThroughApp() throws InvalidElementStateException {
        String methodName = name.getMethodName();
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map loadTime = page.getPageLoad(methodName);
        String pageLoadResult = loadTime.get("result").toString();
        assertEquals(pageLoadResult, "pass");
        Map requests = page.getSpeedIndex(methodName);
        String requestResult = requests.get("result").toString();
        assertEquals(requestResult, "pass");
    }
    @Test
    public void jankinessTest() throws InvalidElementStateException {
        SauceDemoPage.visitPage(driver);
        Map<String, Object> result = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("sauce:jankinessCheck");
        assertTrue("App is too janky", (double) result.get("score") >= .95);
    }
    @Test
    public void ultimateQaJankiness() throws InvalidElementStateException {
        driver.get("https://www.ultimateqa.com");
        Map<String, Object> result = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("sauce:jankinessCheck");
        assertTrue("App is too janky", (double) result.get("score") >= .95);
    }
}
