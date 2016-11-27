package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CartOrderDTO implements Serializable {

    private String deliveryType;
    private String paymentMethod;
    private PersonDTO person;
    private BillingDetailsDTO billingAddress;
}
