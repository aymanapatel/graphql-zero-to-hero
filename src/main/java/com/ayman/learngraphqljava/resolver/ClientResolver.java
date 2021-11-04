package com.ayman.learngraphqljava.resolver;

import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.Client;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public Client client(BankAccount bankAccount) {
        log.info("Requesting client data for bank account id {} ", bankAccount.getId());
        return Client.builder().firstName("John").middleName(List.of("Cox")).lastName("Stewart").build();
    }
}
