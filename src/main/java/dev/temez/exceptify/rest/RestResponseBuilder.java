package dev.temez.exceptify.rest;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public final class RestResponseBuilder<T> {

  @NotNull
  Map<String, Object> errors = new HashMap<>();

  @NotNull
  T payload;

  @NonFinal
  int responseCode;

  @Contract(value = "_,_-> this", pure = true)
  public @NotNull RestResponseBuilder<T> error(
      @NotNull String errorKey,
      @NotNull Object value
  ) throws IllegalStateException {
    if (responseCode == 200) {
      throw new IllegalStateException("Can't add error object to success response");
    }
    errors.put(errorKey, value);
    return this;
  }

  public @NotNull RestResponseBuilder<T> code(int responseCode) {
    this.responseCode = responseCode;
    return this;
  }

  @Contract(" -> new")
  public @NotNull RestResponse<T> build() {
    return new RestResponse<>(responseCode, payload, errors);
  }

  @Contract(" -> new")
  public @NotNull ResponseEntity<RestResponse<T>> toResponseEntity() {
    return build().toResponseEntity();
  }
}
