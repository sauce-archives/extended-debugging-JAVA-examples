package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import java.util.Map;

import static org.junit.Assert.*;

public class SaucePerformanceTest extends TestBase {

    public SaucePerformanceTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Test
    public void verifySaucePerformance() throws InvalidElementStateException {
        String methodName = name.getMethodName();
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map loadTime = page.assertPerformancePageLoad(methodName);
        String pageloadResult = loadTime.get("result").toString();
        assertEquals(pageloadResult, "pass");
        Map requests = page.assertPerformanceSpeedIndex(methodName);
        String requestResult = requests.get("result").toString();
        assertEquals(requestResult, "pass");
    }
}
