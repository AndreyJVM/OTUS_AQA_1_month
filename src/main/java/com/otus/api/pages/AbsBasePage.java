package com.otus.api.pages;

import com.google.inject.Inject;
import com.otus.api.anotations.Path;
import com.otus.api.support.GuiceScoped;
import com.otus.api.utils.AbsBaseUtils;

public abstract class AbsBasePage<T> extends AbsBaseUtils<T> {

  private static final String BASE_URL = System.getProperty("webdriver.base.url");
  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String getPagePath() {
    Class<? extends AbsBasePage> clazz = this.getClass();

    if(clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getAnnotation(Path.class);
      return path.value().endsWith("/") ? path.value() : path.value() + "/"; 
    }

    return "";
  }

  public T open() {
    this.driver.get(BASE_URL + getPagePath());
    return (T) this;
  }
}