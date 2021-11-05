package com.ayman.learngraphqljava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class BalanceService {


    /**
     * Load blances for 2 UUID provded below. Other will be null
     * @param bankAccountIds
     * @param userId
     * @return
     */
    public Map<UUID, BigDecimal> getBalanceFor(Set<UUID> bankAccountIds, String userId) {

        log.info("Service: Request bank account ids: {} and user id: {} ", bankAccountIds, userId       );
        return Map.of(
                UUID.fromString("c6aa269a-812b-49d5-b178-a739a1ed74cc"), BigDecimal.ONE,
                UUID.fromString("48e4a484-af2c-4366-8cd4-25330597473f"), new BigDecimal("23431.22"));
    }
}
