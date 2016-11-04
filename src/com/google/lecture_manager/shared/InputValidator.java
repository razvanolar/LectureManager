package com.google.lecture_manager.shared;

/**
 * Created by razvanolar on 04.11.2016
 */
public class InputValidator {

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }
}
