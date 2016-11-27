package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@Getter
public class CartItem {

    private String productId;
    private Integer quantity;
    private Double price;

    public Double totalPrice() {
        return quantity * price;
    }

    public void add(Integer quantity) {
        this.quantity += quantity;
    }
}
