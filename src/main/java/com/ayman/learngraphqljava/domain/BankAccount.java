package com.ayman.learngraphqljava.domain;


import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.util.UUID;

@Value // immutable variant of @Data; all fields are made private and final
@Builder // builder patter
@Setter
public class BankAccount {

    UUID id;
    String name;
    Currency currency;
}
