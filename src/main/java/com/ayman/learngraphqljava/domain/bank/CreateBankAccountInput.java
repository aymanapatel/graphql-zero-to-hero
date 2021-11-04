package com.ayman.learngraphqljava.domain.bank;

import lombok.Data;

@Data
public class CreateBankAccountInput {

    String firstName;
    int age; // Non-NegativeInt from ScalarConfig.nonNegativeInt
}
