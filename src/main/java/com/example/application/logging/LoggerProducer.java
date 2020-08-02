package com.example.application.logging;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProducer {

    @Produces
    @Default
    @Dependent
    Logger createLogger(final InjectionPoint ip) {
        if (ip.getMember() != null) {
            return Logger.getLogger(ip.getMember().getDeclaringClass());
        } else if (ip.getBean() != null) {
            return Logger.getLogger(ip.getBean().getBeanClass());
        } else {
            return Logger.getLogger("default");
        }
    }
}
