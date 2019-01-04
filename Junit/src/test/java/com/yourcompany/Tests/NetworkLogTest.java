package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.List;

public class NetworkLogTest extends TestBase {

    public NetworkLogTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Test
    public void networkLogShouldHaveMainjs() throws InvalidElementStateException {
        SauceDemoPage page = SauceDemoPage.visitPage(driver);
        page.loginUser();
        page.visitPage("/inventory.html");
        page.sleep(2000);
        List<Map<String, Object>> networkLog = page.getNetwork();
        assertTrue(page.isKeyValueExists(networkLog, "url", "main.js"));
    }

}
