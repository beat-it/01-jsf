package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class CartDTO implements Serializable {

    private List<CartItemDTO> cartItems;
    private PaymentDTO payment;
    private PersonDTO person;
    private BillingDetailsDTO billingDetails;
    private AddressDTO address;

}
