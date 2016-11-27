package org.beat.it.backend.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class Order {

    private Date orderedAt = new Date();
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }
}
