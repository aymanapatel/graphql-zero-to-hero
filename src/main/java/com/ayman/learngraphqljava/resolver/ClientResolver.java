package com.ayman.learngraphqljava.resolver;

import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.Client;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ClientResolver implements GraphQLResolver<BankAccount> {


    /**
     * Simple Resolver
     */
//     public Client client(BankAccount bankAccount) {
//              log.info("Requesting client data for bank account id {} ", bankAccount.getId());
//              return Client.builder().firstName("John").middleName(List.of("Cox")).lastName("Stewart").build();
//     }

    /**
     * Complex DataFetchResult resolver
     * 1. Call multiple services
     * 2. Call another graphql server
     * 3. Call service that returns partial response
     */
//    public DataFetcherResult<Client> client(BankAccount bankAccount) {
//        log.info("Requesting client data for bank account id {} (mutiple services)", bankAccount.getId());
//
//        return DataFetcherResult.<Client>newResult()
//                    .data(Client.builder().id(UUID.randomUUID()).firstName("John").lastName("Stewart").build())
//                    .error(new GenericGraphQLError("Cannot retrieve client id"))
//                    .build();
//
//    }

    /**
     * Async resolver: Client
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );


    public CompletableFuture<Client> client(BankAccount bankAccount) {
        log.info("Completable Future: Client");
        return CompletableFuture.supplyAsync(
                  () -> {
                    log.info("Requesting client data for bank account id {} ", bankAccount.getId());
                    return Client.builder()
                          .firstName("John")
                          .middleName(List.of("Cox"))
                          .lastName("Stewart")
                          .build();
                  }, executorService);
    }

}
