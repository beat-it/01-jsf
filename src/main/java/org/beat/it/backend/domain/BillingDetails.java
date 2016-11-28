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
public class BillingDetails {

    private Address billingAddress;
    private Company company;
}
