package com.otus.api.factories.ipml.configurators.browsers;

import com.otus.api.factories.ipml.configurators.IBrowserConfigure;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfigure implements IBrowserConfigure {

  @Override
  public WebDriver configure() {

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("start-maximized");
    chromeOptions.addArguments("--ignore-certificate-errors");
    chromeOptions.addArguments("--remote-allow-origins=*");
    chromeOptions.addArguments("--homepage=about:blank");

    WebDriverManager.chromedriver().setup();

    return new ChromeDriver(chromeOptions);
  }
}