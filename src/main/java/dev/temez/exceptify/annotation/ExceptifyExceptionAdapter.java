package dev.temez.exceptify.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptifyExceptionAdapter {

  @NotNull ExceptionBinding binding() default @ExceptionBinding;

  @interface ExceptionBinding {

    @NotNull Class<? extends Exception>[] value() default {};

  }
}
