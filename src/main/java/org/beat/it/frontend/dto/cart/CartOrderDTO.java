package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class CartOrderDTO implements Serializable {

    private String deliveryType;
    private String paymentMethod;
    private PersonDTO person;
    private BillingDetailsDTO billingAddress;
    private AddressDTO address;
}
