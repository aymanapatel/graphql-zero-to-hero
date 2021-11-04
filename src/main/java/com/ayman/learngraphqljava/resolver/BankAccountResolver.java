package com.ayman.learngraphqljava.resolver;

import com.ayman.learngraphqljava.domain.BankAccount;
import com.ayman.learngraphqljava.domain.Client;
import com.ayman.learngraphqljava.domain.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id) {
        log.info("Retrieving bank account id: {}", id);

        var clientA = Client.builder().firstName("Jack").lastName("Stewart").build();
        var clientB = Client.builder().firstName("John").middleName(List.of("Cox")).lastName("Stewart").build();

        return BankAccount.builder().id(id).currency(Currency.INR).client(clientA).build();


    }

}
