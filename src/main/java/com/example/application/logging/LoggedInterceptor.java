package com.example.application.logging;

import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Interceptor
@Logged
@Priority(1)
public class LoggedInterceptor {

    private static final String DEFAULT_LOG_STRING = "%s | [%s | %s]: %s";

    @Inject
    Logger logger;

    @AroundInvoke
    public Object invoke(InvocationContext ic) throws Exception {
        Logged logged = getLoggedAnnotation(ic).orElseThrow(() -> new IllegalStateException("No Logged annotation found. Is it hidden in a stereotype"));

        String methodName = ic.getMethod().getName();
        String className = ic.getClass().getSimpleName();
        Logger.Level level = logged.level();

        String input = inParametersToString(ic);
        logger.logf(level, DEFAULT_LOG_STRING, "INPUT", className, methodName, input);

        Object result = ic.proceed();
        String output = outParametersToString(result);
        logger.logf(level, DEFAULT_LOG_STRING, "OUTPUT", className, methodName, output);

        return result;
    }

    private String outParametersToString(Object result) {
        Response response = (Response) result;
        String content;

        if (String.class == response.getEntity().getClass()) {
            content = response.readEntity(String.class);
        } else {
            try {
                content = JsonbBuilder.create().toJson(response.getEntity());
            } catch (Exception e) {
                throw new IllegalArgumentException(this.getClass().getSimpleName()+" failed to serialize a parameter");
            }
        }
        return content;
    }

    private String inParametersToString(InvocationContext ic) {
        return Arrays.stream(ic.getParameters())
                .map(this::serialize)
                .collect(Collectors.joining(","));
    }

    private String serialize(Object obj) {
        if (obj == null) {
            return "null";
        } else
            return JsonbBuilder.create().toJson(obj);
    }

    private Optional<Logged> getLoggedAnnotation(final InvocationContext ic) {
        Logged logged = ic.getMethod().getAnnotation(Logged.class);
        if (logged == null) {
            logged = ic.getTarget().getClass().getAnnotation(Logged.class);
        }

        if (logged == null) {
            return Optional.empty();
        }

        return Optional.of(logged);
    }
}
