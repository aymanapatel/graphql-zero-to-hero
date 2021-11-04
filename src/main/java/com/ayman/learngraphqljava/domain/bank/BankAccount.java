package com.ayman.learngraphqljava.domain.bank;


import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Value // immutable variant of @Data; all fields are made private and final
@Builder // builder patter
@Setter
public class BankAccount {

    UUID id;
    Client client;
    Currency currency;
    ZonedDateTime createdAt;
    LocalDateTime createdOn;
}
