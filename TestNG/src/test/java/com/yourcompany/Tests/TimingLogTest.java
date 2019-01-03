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

public class TimingLogTest extends TestBase {
    @Test(dataProvider = "hardCodedBrowsers")
    public void checkTimingLog(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting SauceDemo page...");
        SauceDemoPage page = SauceDemoPage.visitPage(driver);

        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> timing = page.getTiming();
        Assert.assertTrue(timing.containsKey("domLoading"));
    }

}
