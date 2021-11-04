package com.ayman.learngraphqljava.resolver.bank.mutation;

import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.CreateBankAccountInput;
import com.ayman.learngraphqljava.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BankAccountMutation implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput input) {
        log.info("Mutation: Create bank account for {}", input);

        return BankAccount.builder()
                        .id(UUID.randomUUID())
                        .currency(Currency.INR)
                        .build();
    }
}
