package dev.temez.restify.exception.impl;

import org.jetbrains.annotations.NotNull;

public class AdapterRegistryException extends RestifyException {

  public AdapterRegistryException(@NotNull String message, Object @NotNull ... args) {
    super(message, args);
  }
}
