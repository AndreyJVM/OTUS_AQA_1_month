package com.otus.api.factories.ipml.configurators.browsers;

import com.otus.api.factories.ipml.configurators.IBrowserConfigure;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxConfigure implements IBrowserConfigure {

  @Override
  public WebDriver configure() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("start-maximized");

    WebDriverManager.firefoxdriver().setup();

    return new FirefoxDriver(firefoxOptions);
  }
}