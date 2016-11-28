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
