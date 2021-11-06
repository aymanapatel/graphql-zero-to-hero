package com.ayman.learngraphqljava.resolver.bank.query;

import com.ayman.learngraphqljava.connection.CursorUtil;
import com.ayman.learngraphqljava.context.CustomGraphQLContext;
import com.ayman.learngraphqljava.domain.bank.BankAccount;
import com.ayman.learngraphqljava.domain.bank.Currency;
import com.ayman.learngraphqljava.repository.BankAccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountQueryResolver implements GraphQLQueryResolver {

    private final BankAccountRepository bankAccountRepository;
    private final CursorUtil cursorUtil;

    public BankAccount bankAccount(UUID id, DataFetchingEnvironment environment) {
        log.info("Retrieving bank account id: {}", id);
//        var clientA = Client.builder().firstName("Jack").lastName("Stewart").build();
//        var clientB = Client.builder().firstName("John").middleName(List.of("Cox")).lastName("Stewart").build();
//        clientA.setClient(clientB);
//        clientB.setClient(clientA);


        CustomGraphQLContext context = environment.getContext();
        log.info("UserId : {} ", context.getUserId());

        var requestFields = environment.getSelectionSet().getFields().stream()
                                                        .map(SelectedField::getName).collect(Collectors.toUnmodifiableList());

        log.info("Requested Fields : {} ", requestFields);

        return BankAccount.builder().id(id).currency(Currency.INR).build();
    }


    // Pagination
    public Connection<BankAccount> bankAccounts(int first, @Nullable String cursor) {
        List<Edge<BankAccount>> edges = getBankAccounts(cursor)
                .stream()
                .map(bankAccount -> new DefaultEdge<>(bankAccount,
                        cursorUtil.createCursorWith(bankAccount.getId())))
                .limit(first)
                .collect(Collectors.toUnmodifiableList());

        var pageInfo = new DefaultPageInfo(
                cursorUtil.getFirstCursorFrom(edges),
                cursorUtil.getLastCursorFrom(edges),
                cursor != null,
                edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);
    }

    public List<BankAccount> getBankAccounts(String cursor) {
        if (cursor == null) {
            return bankAccountRepository.getBankAccounts();
        }
        return bankAccountRepository.getBankAccountsAfter(cursorUtil.decode(cursor));
    }

}
