package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BillingDetails {

    private Address billingAddress;
    private Company company;
}
