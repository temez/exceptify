package dev.temez.exceptify.exception.registry;

import dev.temez.exceptify.annotation.ExceptifyExceptionAdapter;
import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.exception.impl.AdapterRegistryException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExceptionAdapterRegistryImpl implements ExceptionAdapterRegistry {

  @NotNull List<AdapterRegistryEntry> registeredAdapters = new LinkedList<>();

  @Override
  @SuppressWarnings({"unchecked"})
  public <T extends Exception> @NotNull ExceptionAdapter<T> getAdapter(
      @NotNull Class<T> exceptionClass
  ) throws AdapterRegistryException {
    return (ExceptionAdapter<T>) registeredAdapters.stream()
        .filter(entry -> entry.getExceptionClasses().contains(exceptionClass))
        .map(AdapterRegistryEntry::getExceptionAdapter)
        .findFirst()
        .orElseThrow(() ->
            new AdapterRegistryException("Adapter not found for %s", exceptionClass.getSimpleName())
        );

  }

  @Override
  public boolean isRegistered(@NotNull Class<?> exceptionClass) {
    return registeredAdapters.stream()
        .anyMatch(entry -> entry.getExceptionClasses().contains(exceptionClass));
  }

  @Override
  public void register(
      @NotNull ExceptionAdapter<?> exceptionAdapter
  ) throws AdapterRegistryException {
    Class<?> exceptionAdapterClass = exceptionAdapter.getClass();
    ExceptifyExceptionAdapter exceptionAdapterAnnotation = exceptionAdapterClass
        .getAnnotation(ExceptifyExceptionAdapter.class);

    List<Class<? extends Exception>> exceptions = Arrays
        .stream(exceptionAdapterAnnotation.binding().value())
        .peek(exceptionClass -> {
          if (isRegistered(exceptionClass)) {
            throw new AdapterRegistryException("ExceptionAdapter for %s already registered",
                exceptionClass.getSimpleName()
            );
          }
        })
        .toList();

    AdapterRegistryEntry entry = new AdapterRegistryEntry(
        exceptions,
        exceptionAdapter
    );
    registeredAdapters.add(entry);
    log.debug(
        "Registered {} as ExceptionAdapter for {}",
        exceptionAdapter.getClass().getSimpleName(),
        exceptions.stream().map(Class::getSimpleName).collect(Collectors.joining(", "))
    );
  }

  @Getter
  @RequiredArgsConstructor
  @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
  private static final class AdapterRegistryEntry {

    @NotNull List<Class<? extends Exception>> exceptionClasses;

    @NotNull ExceptionAdapter<?> exceptionAdapter;

  }
}
