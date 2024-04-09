package dev.temez.exceptify.exception.adapter;

import dev.temez.exceptify.rest.RestResponse;
import org.jetbrains.annotations.NotNull;

public interface ExceptionAdapter<T extends Exception> {

  @NotNull RestResponse<?> convert(@NotNull T exception);

}
