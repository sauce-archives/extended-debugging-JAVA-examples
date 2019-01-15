package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;
import java.util.Map;

public class TimingLogTest extends TestBase {

    public TimingLogTest(String os,
                          String version, String browser, String deviceName, String deviceOrientation) {
            super(os, version, browser, deviceName, deviceOrientation);
    }

    @Test
    public void checkTimingLog() throws InvalidElementStateException {
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        Map<String, Object> timing = page.getTiming();
        assertTrue(timing.containsKey("domLoading"));
    }

}
