package dev.temez.exceptify.exception.impl;

import org.jetbrains.annotations.NotNull;

public abstract class ExceptifyException extends RuntimeException {

  public ExceptifyException() {
  }

  public ExceptifyException(@NotNull String message) {
    super(message);
  }

  public ExceptifyException(@NotNull String message, Object @NotNull ... args) {
    super(String.format(message, args));
  }

  public ExceptifyException(@NotNull String message, @NotNull Throwable cause) {
    super(message, cause);
  }

  public ExceptifyException(@NotNull Throwable cause) {
    super(cause);
  }

  public ExceptifyException(@NotNull String message, @NotNull Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
