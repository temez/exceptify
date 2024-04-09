package dev.temez.exceptify.exception.impl;

import org.jetbrains.annotations.NotNull;

public class AdapterRegistryException extends ExceptifyException {

  public AdapterRegistryException(@NotNull String message, Object @NotNull ... args) {
    super(message, args);
  }
}
