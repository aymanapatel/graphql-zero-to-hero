package com.ayman.learngraphqljava.exception;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Component
public class GraphQLExceptionHandler {


//    @ExceptionHandler(GraphQLException.class)
//    public ThrowableGraphQLError handle(GraphQLException graphQLException) {
//        return new ThrowableGraphQLError(graphQLException); // throw new GraphQLException("Resolver error: Client");
//    }

    @ExceptionHandler({GraphQLException.class, ConstraintViolationException.class})
    public ThrowableGraphQLError handle(Exception exception) {
        return new ThrowableGraphQLError(exception); // throw new GraphQLException("Resolver error: Client");
    }



    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException exception) {
            return new ThrowableGraphQLError(exception, "Internal Server error"); //throw new RuntimeException("Spring Error: Cannot do [select * from users]");
    }

}
