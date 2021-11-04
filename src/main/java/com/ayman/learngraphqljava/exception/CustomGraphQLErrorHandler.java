package com.ayman.learngraphqljava.exception;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {


    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errorList) {
        return errorList;
    }
}
