package dev.temez.restify.configuration;

import dev.temez.restify.exception.adapter.ExceptionAdapter;
import dev.temez.restify.exception.registry.ExceptionAdapterRegistry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RestifyExceptionHandler {

  @NotNull ExceptionAdapterRegistry exceptionAdapterRegistry;

  @SuppressWarnings("unchecked")
  @ExceptionHandler(value = Exception.class)
  public <T extends Exception> @NotNull ResponseEntity<?> handle(@NotNull T exception) throws T {
    Class<T> exceptionClass = (Class<T>) exception.getClass();
    if (exceptionAdapterRegistry.isRegistered(exception.getClass())) {
      ExceptionAdapter<T> exceptionAdapter = exceptionAdapterRegistry.getAdapter(exceptionClass);
      return exceptionAdapter.convert(exception).toResponseEntity();
    }
    throw exception;
  }
}
