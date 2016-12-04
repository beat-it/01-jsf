package org.beat.it.frontend.jsf.producer;

import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.service.CartService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

public class CartProducer {

    @Inject
    private CartService cartService;

    @Named
    @Produces
    public Cart getCart() {
        if (cartService.getCart() == null) {
            cartService.initCart();
        }
        return cartService.getCart();
    }
}
