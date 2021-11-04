package com.ayman.learngraphqljava.domain.bank;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBankAccountInput {

    @NotBlank
    String firstName;
    int age; // Non-NegativeInt from ScalarConfig.nonNegativeInt
}
