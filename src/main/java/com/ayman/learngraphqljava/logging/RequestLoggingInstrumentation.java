package com.ayman.learngraphqljava.logging;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    public static final String CORRELATION_ID = "correlation_id";

    private final Clock clock;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var start = Instant.now(clock);

        MDC.put(CORRELATION_ID, parameters.getExecutionInput().getExecutionId().toString());
//        log.info("Logging: Query {} with variables: {} ", parameters.getQuery(), parameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted(((executionResult, throwable) -> {
            var duration = Duration.between(start, Instant.now(clock));
            if (throwable == null) {
//              log.info("Logging Successful Query in {}", duration);
            } else {
                log.warn("Logging failed in {} Throwing: {}", duration, throwable);
            }
            // If we have async resolvers, this callback can occur in the thread-pool and not the NIO thread.
            // In that case, the LoggingListener will be used as a fallback to clear the NIO thread.
            MDC.clear();
        }));


    }

}
