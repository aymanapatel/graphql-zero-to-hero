package com.ayman.learngraphqljava.listener;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingListener implements GraphQLServletListener {


//    private final Clock clock;

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
//        log.info("Start: GraphQL Request started");
//        var startTime = Instant.now(clock);
        return new RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
                //no-op
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                log.error("Caught exception in listener.", throwable);
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                // This callback will be called post graphql lifecycle.
                // If we are multi-threading we can clear the original NIO thread MDC variables here.
                MDC.clear();
            }

        };
    }


}
