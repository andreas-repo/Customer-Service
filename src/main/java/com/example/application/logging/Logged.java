package com.example.application.logging;

import org.jboss.logging.Logger;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Logged {

    @Nonbinding
    Logger.Level level() default Logger.Level.INFO;
}
