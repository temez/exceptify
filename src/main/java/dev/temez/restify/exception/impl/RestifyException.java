package dev.temez.restify.exception.impl;

import org.jetbrains.annotations.NotNull;

public class RestifyException extends RuntimeException {

  public RestifyException() {
  }

  public RestifyException(@NotNull String message) {
    super(message);
  }

  public RestifyException(@NotNull String message, Object @NotNull ... args) {
    super(String.format(message, args));
  }

  public RestifyException(@NotNull String message, @NotNull Throwable cause) {
    super(message, cause);
  }

  public RestifyException(@NotNull Throwable cause) {
    super(cause);
  }

  public RestifyException(@NotNull String message, @NotNull Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
