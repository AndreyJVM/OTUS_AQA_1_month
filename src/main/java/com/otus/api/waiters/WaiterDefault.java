package com.otus.api.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class WaiterDefault {

  private WebDriver driver = null;

  public WaiterDefault(WebDriver driver) {
    this.driver = driver;
  }

  private final long second = 5;

  public boolean isWaitForCondition(ExpectedCondition condition) {
    WebDriverWait webDriverWait = new WebDriverWait(driver, second);
    try {
      webDriverWait.until(condition);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public WebElement waitingForElement(By by) {
    try {
      isWaitForElementVisible(by);
      isWaitForElementClickable(by);

      return driver.findElement(by);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<WebElement> waitingForElements(By by) {
    try {
      isWaitForElementVisible(by);

      return driver.findElements(by);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public boolean isWaitForElementVisible(By by) {
    return isWaitForCondition(ExpectedConditions
            .visibilityOfElementLocated(by));
  }

  public boolean isWaitForElementClickable(By by) {
    return isWaitForCondition(ExpectedConditions
            .elementToBeClickable(by));
  }
}