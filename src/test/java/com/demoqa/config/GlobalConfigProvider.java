package com.demoqa.config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class GlobalConfigProvider {

    GlobalConfig config = ConfigFactory.create(GlobalConfig.class, System.getProperties());

    public void setUp() {
        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.baseUrl = config.baseUrl();
        RestAssured.baseURI = config.baseUrl();
        Configuration.pageLoadStrategy = "eager";

        if(config.isRemote()){
            Configuration.remote = config.remoteDriver();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}
