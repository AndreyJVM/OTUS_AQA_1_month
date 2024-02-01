package com.otus.api.support;

import com.otus.api.factories.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  private WebDriver driver = new WebDriverFactory().create(getBrowserName());

  private String browserName;

  public WebDriver getDriver() {
    return driver;
  }

  public void setBrowserName(String browserName) {
    this.browserName = browserName;
  }

  public String getBrowserName() {
    return browserName;
  }
}