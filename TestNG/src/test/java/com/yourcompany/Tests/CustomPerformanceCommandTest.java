package com.yourcompany.Tests;

import com.yourcompany.Pages.SauceDemoPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.Map;

public class CustomPerformanceCommandTest extends TestBase {
    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyHelloOutput(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting SauceDemo page...");
        SauceDemoPage page = SauceDemoPage.visitPage(driver);

        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map pageLoad = page.assertPerformancePageLoad(method.getName());
        String pageloadResult = pageLoad.get("result").toString();
        Assert.assertEquals(pageloadResult, "pass");
        Map requests = page.assertPerformancePageWeight(method.getName());
        String requestResult = requests.get("result").toString();
        Assert.assertEquals(requestResult, "pass");
    }

}
