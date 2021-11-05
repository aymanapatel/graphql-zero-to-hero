package com.ayman.learngraphqljava.context;

import com.ayman.learngraphqljava.context.dataloader.DataLoaderRegistryFactory;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private final DataLoaderRegistryFactory dataLoaderRegistryFactory;

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {

        var userId = httpServletRequest.getHeader("user_id");

        var context = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderRegistryFactory.create(userId))
                .build();

        return new CustomGraphQLContext(userId, context);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return null;
    }

    /**
     * This builder is used for Websockets. Since we are not supporting Websocket we are thorwing exception
     * @return
     */
    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("Unsupported Websocket builder");
    }
}
