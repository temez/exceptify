package dev.temez.exceptify.initializer;

import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.exception.registry.ExceptionAdapterRegistry;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExceptionAdapterInitializer implements BeanPostProcessor {

  @NotNull ExceptionAdapterRegistry exceptionAdapterRegistry;

  @Lazy
  public ExceptionAdapterInitializer(@NotNull ExceptionAdapterRegistry exceptionAdapterRegistry) {
    this.exceptionAdapterRegistry = exceptionAdapterRegistry;
  }

  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean,
                                                        @NotNull String beanName) {
    if (bean instanceof ExceptionAdapter<?> exceptionAdapter) {
      exceptionAdapterRegistry.register(exceptionAdapter);
    }
    return bean;
  }
}
