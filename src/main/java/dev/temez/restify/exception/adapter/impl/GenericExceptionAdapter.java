package dev.temez.restify.exception.adapter.impl;

import dev.temez.restify.annotation.RestifyExceptionAdapter;
import dev.temez.restify.exception.adapter.ExceptionAdapter;
import dev.temez.restify.exception.impl.RestifyException;
import dev.temez.restify.rest.RestResponse;
import org.jetbrains.annotations.NotNull;

@RestifyExceptionAdapter(
    binding = @RestifyExceptionAdapter.ExceptionBinding(RestifyException.class)
)
public final class GenericExceptionAdapter implements ExceptionAdapter<RestifyException> {

  @Override
  public @NotNull RestResponse<?> convert(@NotNull RestifyException exception) {
    return RestResponse.badRequest()
        .code(400)
        .error("exception", exception.getClass().getSimpleName())
        .build();
  }
}
