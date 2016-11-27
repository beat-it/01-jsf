package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Person {

    private String forename;
    private String surname;
    private String email;
    private String phone;

}
