package com.ayman.learngraphqljava.resolver;

import com.ayman.learngraphqljava.domain.bank.Asset;
import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.Client;
import com.ayman.learngraphqljava.util.CorrelationIdPropagationExecutor;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class AssetResolver implements GraphQLResolver<BankAccount> {

    /**
     * Async resolver: Asset
     */
    private final Executor executor= CorrelationIdPropagationExecutor.wrap(Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    ));


    public CompletableFuture<List<Asset>> assets(BankAccount bankAccount) {
        return CompletableFuture.supplyAsync(
                () -> {
                    log.info("Getting assets for bank account id {}", bankAccount.getId());
                    return List.of();
                },
                executor);
    }
}
