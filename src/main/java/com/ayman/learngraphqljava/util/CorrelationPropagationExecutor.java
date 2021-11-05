package com.ayman.learngraphqljava.util;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;

import static com.ayman.learngraphqljava.logging.RequestLoggingInstrumentation.CORRELATION_ID;

@RequiredArgsConstructor
public class CorrelationPropagationExecutor implements Executor {

    private final Executor delegate;

    public static final Executor wrap(Executor executor) {
        return new CorrelationPropagationExecutor(executor);
    }

    @Override
    public void execute(@NotNull Runnable command) {

        var correlationId = MDC.get(CORRELATION_ID);

        delegate.execute(() -> {
            try {
                MDC.put(CORRELATION_ID, correlationId);
            } finally {
                MDC.remove(CORRELATION_ID);
            }
        });

    }
}
