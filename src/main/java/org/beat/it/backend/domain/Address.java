package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {

    private String street;
    private String city;
    private String zip;
    private String country;
}
