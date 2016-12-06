package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@Getter
public class PaymentMethod {

    private String id;
    private Double price;
    private String name;
}
