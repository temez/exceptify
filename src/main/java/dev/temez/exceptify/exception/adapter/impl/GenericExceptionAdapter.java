package dev.temez.exceptify.exception.adapter.impl;

import dev.temez.exceptify.annotation.ExceptifyExceptionAdapter;
import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.exception.impl.ExceptifyException;
import dev.temez.exceptify.rest.RestResponse;
import org.jetbrains.annotations.NotNull;

@ExceptifyExceptionAdapter(
    binding = @ExceptifyExceptionAdapter.ExceptionBinding(ExceptifyException.class)
)
public final class GenericExceptionAdapter implements ExceptionAdapter<ExceptifyException> {

  @Override
  public @NotNull RestResponse<?> convert(@NotNull ExceptifyException exception) {
    return RestResponse.badRequest()
        .code(400)
        .error("exception", exception.getClass().getSimpleName())
        .build();
  }
}
