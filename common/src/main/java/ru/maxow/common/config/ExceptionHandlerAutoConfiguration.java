package ru.maxow.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.maxow.common.util.GlobalExceptionHandler;

@Configuration
@Import(GlobalExceptionHandler.class)
public class ExceptionHandlerAutoConfiguration {
}
