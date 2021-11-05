package com.ayman.learngraphqljava.resolver.bank.query;

import com.ayman.learngraphqljava.context.dataloader.DataLoaderRegistryFactory;
import com.ayman.learngraphqljava.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Component
public class BankAccountResolver implements GraphQLResolver<BankAccount> {


//    public BigDecimal balances(BankAccount bankAccount) {
//
//        log.info("Getting balance for {}", bankAccount.getId());
//
//        return BigDecimal.ONE;
//    }


    public CompletableFuture<BigDecimal> balances(BankAccount bankAccount, DataFetchingEnvironment environment) {

        log.info("Getting balance for {}", bankAccount.getId());
        DataLoader<UUID, BigDecimal> dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER);

        return dataLoader.load(bankAccount.getId());
    }
}
