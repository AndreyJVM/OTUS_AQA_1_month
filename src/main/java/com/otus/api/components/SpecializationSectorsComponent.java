package com.otus.api.components;

import com.otus.api.anotations.Component;
import com.otus.api.pages.MainPage;
import com.otus.api.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("//h2[contains(text(),'Специализации')]/ancestor::section")
public class SpecializationSectorsComponent extends AbsComponent{

  public SpecializationSectorsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  String startCourseLocator = baseComponentLocator + "//span[@class='sc-12yergf-7 dPBnbE']";

  public MainPage getEarliestStartCourse() {

    List<WebElement> dateInfoElements = waiterDefault.waitingForElements(By.xpath(startCourseLocator));
    Map<LocalDate, WebElement> coursesMap = new HashMap<>();
    LocalDate startCourseDate;

    for (WebElement element : dateInfoElements) {

      startCourseDate = parseDate(element.getText());

      if (startCourseDate != null)
        coursesMap.put(startCourseDate, element);
    }

    startCourseDate = coursesMap.keySet().stream()
            .reduce((first, current) -> current.isBefore(first) ? current : first)
            .get();

    actions
            .click(coursesMap.get(startCourseDate))
            .click()
            .perform();

    return new MainPage(guiceScoped);
  }

  public MainPage getLatestStartCourse() {

    List<WebElement> dateInfoElements = waiterDefault.waitingForElements(By.xpath(startCourseLocator));
    Map<LocalDate, WebElement> coursesMap = new HashMap<>();
    LocalDate startCourseDate;

    for (WebElement element : dateInfoElements) {

      startCourseDate = parseDate(element.getText());

      if (startCourseDate != null)
        coursesMap.put(startCourseDate, element);
    }

    startCourseDate = coursesMap.keySet().stream()
            .reduce((last, current) -> current.isAfter(last) ? current : last)
            .get();

    actions
            .click(coursesMap.get(startCourseDate))
            .click()
            .perform();

    return new MainPage(guiceScoped);
  }
}