package com.yourcompany.Tests;

import com.yourcompany.Pages.SauceDemoPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class PerformanceTest extends TestBase {
    @Test(dataProvider = "hardCodedBrowsers")
    public void checkPerformanceLog(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting SauceDemo page...");
        SauceDemoPage page = SauceDemoPage.visitPage(driver);

        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> performance = page.getPerformance();

        // Checking metrics existence

        Assert.assertTrue(performance.containsKey("load"));
        Assert.assertTrue(performance.containsKey("speedIndex"));
        Assert.assertTrue(performance.containsKey("pageWeight"));
        Assert.assertTrue(performance.containsKey("pageWeightEncoded"));
        Assert.assertTrue(performance.containsKey("timeToFirstByte"));
        Assert.assertTrue(performance.containsKey("timeToFirstInteractive"));
        Assert.assertTrue(performance.containsKey("firstContentfulPaint"));
        Assert.assertTrue(performance.containsKey("perceptualSpeedIndex"));
        Assert.assertTrue(performance.containsKey("domContentLoaded"));
    }

}
