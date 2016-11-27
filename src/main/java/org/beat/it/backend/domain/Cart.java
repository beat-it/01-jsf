package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart implements Serializable {

    private List<CartItem> cartItems = new ArrayList<>();
    private Payment payment;
    private Person person;
    private BillingDetails billingDetails;
    private Address address;

    public Double getTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem::totalPrice).sum();
    }

    public Double getItemsPrice() {
        return payment != null ? getTotalPrice() - payment.getDeliveryPrice() : getTotalPrice();
    }
}
