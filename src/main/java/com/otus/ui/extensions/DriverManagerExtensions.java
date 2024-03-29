package com.otus.ui.extensions;

import com.otus.ui.anotations.Driver;
import com.otus.ui.factories.WebDriverFactory;
import com.otus.ui.listeners.WebDriverListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class DriverManagerExtensions implements BeforeEachCallback, AfterEachCallback {

  private EventFiringWebDriver driver = null;

  private Set<Field> getFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
    Set<Field> fields = new HashSet<>();
    Class<?> testClass = extensionContext.getTestClass().get();
    for (Field field : testClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(annotation)) {
        fields.add(field);
      }
    }
    return fields;
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new WebDriverFactory()
            .create()
            .register(new WebDriverListener());

    var fieldsToInject = getFields(Driver.class, extensionContext);
    for (Field field : fieldsToInject) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {
        field.setAccessible(true);
        field.set(extensionContext.getTestInstance().get(), driver);
      }
    }
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }
}