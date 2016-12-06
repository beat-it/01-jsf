package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class PaymentMethodDTO implements Serializable {

    private String id;
    private Double price;
    private String currency = "EUR";
    private String name;
}
