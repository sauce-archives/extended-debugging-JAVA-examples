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

public class MetricsLogTest extends TestBase {
    @Test(dataProvider = "hardCodedBrowsers")
    public void pageLoadTimeShouldLessThan5s(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting SauceDemo page...");
        SauceDemoPage page = SauceDemoPage.visitPage(driver);

        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> metrics = page.getMetrics();
        double pageLoadTime = new Double(metrics.get("domContentLoaded").toString()) - new Double(metrics.get("navigationStart").toString());
        Assert.assertTrue(pageLoadTime < 5);
    }

}
