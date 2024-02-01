package com.otus.api.factories.ipml.configurators.browsers;

import com.otus.api.factories.ipml.configurators.IBrowserConfigure;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaConfigure implements IBrowserConfigure {

  @Override
  public WebDriver configure() {

    OperaOptions operaOptions = new OperaOptions();
    operaOptions.addArguments("start-maximized");

    WebDriverManager.operadriver().setup();
    return new OperaDriver(operaOptions);
  }
}