package dev.temez.exceptify.exception.registry;

import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.exception.impl.AdapterRegistryException;
import org.jetbrains.annotations.NotNull;

public interface ExceptionAdapterRegistry {

  <T extends Exception> @NotNull ExceptionAdapter<T> getAdapter(@NotNull Class<T> exceptionClass)
      throws AdapterRegistryException;

  boolean isRegistered(@NotNull Class<?> exceptionClass);

  void register(@NotNull ExceptionAdapter<?> exceptionAdapter);
}
