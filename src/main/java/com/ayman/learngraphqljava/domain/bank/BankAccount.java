package com.ayman.learngraphqljava.domain.bank;


import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Value // immutable variant of @Data; all fields are made private and final
@Builder // builder patter
@Setter
public class BankAccount {

    UUID id;
    Client client;
    Currency currency;
    List<Asset> assets;
    ZonedDateTime createdAt;
    LocalDateTime createdOn;
    BigDecimal balances;
}
