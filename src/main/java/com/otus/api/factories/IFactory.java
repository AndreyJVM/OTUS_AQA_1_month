package com.otus.api.factories;

import com.otus.api.exceptions.BrowserNotSupportedException;

public interface IFactory<T> {
  T create(String browserName) throws BrowserNotSupportedException;
}