package com.ayman.learngraphqljava.domain.bank;

import lombok.Builder;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Builder
public class Client {

    UUID id;
    String firstName;
    List<String> middleName;
    String lastName;
    Client client;


}
