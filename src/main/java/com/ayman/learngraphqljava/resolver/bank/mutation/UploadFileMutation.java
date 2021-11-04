package com.ayman.learngraphqljava.resolver.bank.mutation;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class UploadFileMutation implements GraphQLMutationResolver {

    public UUID uploadFile(DataFetchingEnvironment dataFetchingEnvironment) {

        DefaultGraphQLServletContext graphQLServletContext =  dataFetchingEnvironment.getContext();

        graphQLServletContext.getFileParts().forEach(
                part -> log.info("Uploading File Name: {}; File Size: {}", part.getSubmittedFileName(), part.getSize())
        );


        return UUID.randomUUID();
    }
}

