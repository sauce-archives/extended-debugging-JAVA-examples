package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;
import java.util.Map;

public class MetricsLogTest extends TestBase {

    public MetricsLogTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    /**
     * Runs a simple test verifying pageload time should be less than 5s
     * @throws InvalidElementStateException
     */
    @Test
    public void pageLoadTimeShouldLessThan5s() throws InvalidElementStateException {
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> metrics = page.getMetrics();
        double pageLoadTime = new Double(metrics.get("domContentLoaded").toString()) - new Double(metrics.get("navigationStart").toString());
        assertTrue(pageLoadTime < 5);
    }

}
