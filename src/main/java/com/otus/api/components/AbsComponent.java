package com.otus.api.components;

import com.google.inject.Inject;
import com.otus.api.anotations.Component;
import com.otus.api.support.GuiceScoped;
import com.otus.api.utils.AbsBaseUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public abstract class AbsComponent<T> extends AbsBaseUtils<T> {
  @Inject
  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  {
    Assertions.assertTrue(waiterDefault
                    .isWaitForCondition(ExpectedConditions
                    .visibilityOfElementLocated(getComponentLocator())));
  }

  protected String baseComponentLocator;

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      String path = component.value();
      baseComponentLocator = path;

      return path.startsWith("/") ? By.xpath(path) : By.cssSelector(path);
    }
    return null;
  }

  public WebElement getComponentElement(String path) {
    return path.startsWith("/") ? waiterDefault.waitingForElement(By.xpath(path)) :
            waiterDefault.waitingForElement(By.cssSelector(path));
  }

  public List<WebElement> getComponentElements(String path) {
    return path.startsWith("/") ? waiterDefault.waitingForElements(By.xpath(path)) :
            waiterDefault.waitingForElements(By.cssSelector(path));
  }

  public LocalDate parseDate(String input) {

    String formatted = input
            .replace("С ", "")
            .replaceAll("\\bгода\\b", "")
            .replaceAll(" \\d+ (месяцев|месяца)$", "")
            .trim();

    if (!formatted.matches("\\d{4}$")) {
      formatted += " " + LocalDate.now().getYear();
    }

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("d MMMM yyyy")
            .withLocale(new Locale("ru"));

    return LocalDate.parse(formatted, formatter);
  }
}