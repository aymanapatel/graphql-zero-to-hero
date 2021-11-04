package com.ayman.learngraphqljava.resolver.bank.mutation;

import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.CreateBankAccountInput;
import com.ayman.learngraphqljava.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Validated // Validate POJO validation `@NotBlank firstName`
@Slf4j
public class BankAccountMutation implements GraphQLMutationResolver {

    private final Clock clock;

    public BankAccount createBankAccount(@Valid  CreateBankAccountInput input) {
        log.info("Mutation: Create bank account for {}", input);

        return BankAccount.builder()
                        .id(UUID.randomUUID())
                        .currency(Currency.INR)
                        .createdAt(ZonedDateTime.now(clock))
                        .createdOn(LocalDateTime.now(clock))
                        .build();
    }
}
