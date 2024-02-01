package com.otus.api.pages;

import com.google.inject.Inject;
import com.otus.api.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  //Локаторы Xpath
  //Раздел популярные курсы: первый курс
  @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/main/div/section[1]/div/div[1]")
  private WebElement popularFirstCourse;

  //Раздел специализация: первый курс
  @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/main/div/section[2]/div/div[1]")
  private WebElement specializationSectionFirstCourse;


  public void popularFirstCourseClick() {
    popularFirstCourse.click();
  }

  public void specializationFirstCourseClick() {
    specializationSectionFirstCourse.click();
  }

  public Optional<WebElement> findCourseByNameSpecialization(String findNameCourse) {
    return driver.findElements(By.cssSelector(".fgNPoG"))
            .stream()
            .filter(element -> element.getText().contains(findNameCourse))
            .findFirst();
  }
}