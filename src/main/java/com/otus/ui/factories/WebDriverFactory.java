package com.otus.ui.factories;

import com.otus.ui.exceptions.BrowserNotSupportedException;
import com.otus.ui.factories.ipml.configurators.browsers.ChromeConfigure;
import com.otus.ui.factories.ipml.configurators.browsers.FirefoxConfigure;
import com.otus.ui.factories.ipml.configurators.browsers.OperaConfigure;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory implements IFactory<EventFiringWebDriver> {

  @Override
  public EventFiringWebDriver create(String browserName) {

    switch (browserName) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeConfigure().configure());
      }
      case "firefox": {
        return new EventFiringWebDriver(new FirefoxConfigure().configure());
      }
      case "opera": {
        return new EventFiringWebDriver(new OperaConfigure().configure());
      }
      default: {
        throw new BrowserNotSupportedException(browserName);
      }
    }
  }
}