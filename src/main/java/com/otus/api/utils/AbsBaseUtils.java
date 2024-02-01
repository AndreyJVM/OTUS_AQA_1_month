package com.otus.api.utils;

import com.otus.api.support.GuiceScoped;
import com.otus.api.waiters.WaiterDefault;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsBaseUtils<T> {

  protected WebDriver driver;
  protected WaiterDefault waiterDefault;
  protected Actions actions;
  protected GuiceScoped guiceScoped;

  public AbsBaseUtils(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.getDriver();
    this.waiterDefault = new WaiterDefault(driver);
    this.actions = new Actions(driver);

    PageFactory.initElements(driver, this);
  }
}