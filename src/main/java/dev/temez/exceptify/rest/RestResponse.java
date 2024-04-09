package dev.temez.exceptify.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
public class RestResponse<T> {

  @JsonIgnore
  int responseCode;

  @JsonProperty("success")
  boolean successful;

  @NotNull
  Date timestamp = new Date();

  @NotNull T payload;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  Map<String, Object> errors;

  @JsonCreator
  public RestResponse(int responseCode,
                      @NotNull T payload,
                      Map<String, Object> errors) {
    this.responseCode = responseCode;
    this.successful = responseCode == 200;
    this.payload = payload;
    this.errors = errors;
  }

  @Contract("_ -> new")
  public static <T> @NotNull RestResponseBuilder<T> ok(@NotNull T payload) {
    return new RestResponseBuilder<>(payload, 200);
  }

  @Contract("-> new")
  public static @NotNull RestResponseBuilder<String> ok() {
    return new RestResponseBuilder<>("", 200);
  }

  @Contract("_ -> new")
  public static <T> @NotNull RestResponseBuilder<T> badRequest(@NotNull T payload) {
    return new RestResponseBuilder<>(payload, 400);
  }

  @Contract("-> new")
  public static @NotNull RestResponseBuilder<String> badRequest() {
    return new RestResponseBuilder<>("", 400);
  }

  @Contract(" -> new")
  public @NotNull ResponseEntity<RestResponse<T>> toResponseEntity() {
    return ResponseEntity.status(responseCode).body(this);
  }
}
