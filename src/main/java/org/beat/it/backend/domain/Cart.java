package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class Cart implements Serializable {

    private List<CartItem> cartItems = new ArrayList<>();
    private Payment payment;
    private Person person;
    private BillingDetails billingDetails;
    private Address address;

    public Double getItemsPrice() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public Double getTotalPrice() {
        return payment != null ? payment.getTotalPrice() : getItemsPrice();
    }

    public Integer getItemsCount() {
        return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public Payment getPayment() {
        if (this.payment == null) {
            Payment computed = new Payment(getItemsPrice(), getItemsPrice(), 0.0d,
                    null, 0d, null);
            return computed;
        } else {
            return this.payment;
        }
    }
}
