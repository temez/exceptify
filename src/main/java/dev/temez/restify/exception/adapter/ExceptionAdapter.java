package dev.temez.restify.exception.adapter;

import dev.temez.restify.rest.RestResponse;
import org.jetbrains.annotations.NotNull;

public interface ExceptionAdapter<T extends Exception> {

  @NotNull RestResponse<?> convert(@NotNull T exception);

}
