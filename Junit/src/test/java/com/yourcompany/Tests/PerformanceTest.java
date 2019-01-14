package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;
import java.util.Map;

public class PerformanceTest extends TestBase {


    public PerformanceTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Test
    public void checkPerformanceLog() throws InvalidElementStateException {
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> performance = page.getPerformance();

        // Checking metrics existence
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

}
