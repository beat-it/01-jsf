package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@Getter
@ToString
public class Address {

    private String street;
    private String city;
    private String zip;
    private String country;
}
