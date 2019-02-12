package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;

public class SaucePerformanceTest extends TestBase {

    public SaucePerformanceTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Test
    public void verifyHelloOutput() throws InvalidElementStateException {
        String methodName = name.getMethodName();
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Boolean loadTime = page.assertPerformance(methodName);
        assertTrue(loadTime);
        Boolean requests = page.assertRequests(methodName);
        assertTrue(requests);
    }

}
