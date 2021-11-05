package com.ayman.learngraphqljava.logging;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLoggingInstrumentation extends SimpleInstrumentation {


    private final Clock clock;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var start = Instant.now(clock);

        var executionId = parameters.getExecutionInput().getExecutionId();

        log.info("Logging: {}: query with variables: {} ", executionId, parameters.getQuery());

        return SimpleInstrumentationContext.whenCompleted(((executionResult, throwable) -> {
            var duration = Duration.between(start, Instant.now(clock));
            if (throwable == null) {
              log.info("Logging {}: Successful Query in {}", executionId, duration);
            } else {
                log.warn("Logging {}: failed in {} Throwing: {}", executionId, duration, throwable);
            }
        }));


    }

}
