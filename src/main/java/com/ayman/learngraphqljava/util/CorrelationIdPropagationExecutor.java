package com.ayman.learngraphqljava.util;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;

import static com.ayman.learngraphqljava.logging.RequestLoggingInstrumentation.CORRELATION_ID;

@RequiredArgsConstructor(staticName = "wrap")
public class CorrelationIdPropagationExecutor implements Executor {

    private final Executor delegate;

    @Override
    public void execute(@NotNull Runnable command) {
        var correlationId = MDC.get(CORRELATION_ID);
        delegate.execute(() -> {
            try {
                MDC.put(CORRELATION_ID, correlationId);
                command.run();
            } finally {
                MDC.remove(CORRELATION_ID);
            }
        });
    }

}