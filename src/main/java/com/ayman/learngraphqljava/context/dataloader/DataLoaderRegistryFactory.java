package com.ayman.learngraphqljava.context.dataloader;

import com.ayman.learngraphqljava.service.BalanceService;
import com.ayman.learngraphqljava.util.CorrelationIdPropagationExecutor;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

    private final BalanceService balanceService;
    public  static final String BALANCE_DATA_LOADER = "BALANCE_DATA_LOADER";

    private static final Executor balanceThreadPool = CorrelationIdPropagationExecutor.wrap(
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    /**
     * Batch balance response for each query
     * @param userId
     * @return
     */
    public DataLoaderRegistry create(String userId) {
        var registry = new DataLoaderRegistry();


        registry.register(BALANCE_DATA_LOADER, createBalanceDataLoader(userId));
        return registry;
    }

    private DataLoader<UUID, BigDecimal> createBalanceDataLoader(String userId) {


        // a a b would give ab for DataLoader.getDataLaoder
        return DataLoader.newMappedDataLoader((Set<UUID> bankAccountIds) -> {
            return CompletableFuture.supplyAsync( () -> {
                return balanceService.getBalanceFor(bankAccountIds, userId);
            }, balanceThreadPool);
        });
    }
}
