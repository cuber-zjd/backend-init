package com.cuber.backend.system.annotation;

import com.cuber.backend.system.enums.SystemLoggingLevel;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface SystemLogging {

    SystemLoggingLevel level() default SystemLoggingLevel.INFO;

    boolean logParams() default true;

    boolean logResult() default true;

    boolean logException() default true;

}
