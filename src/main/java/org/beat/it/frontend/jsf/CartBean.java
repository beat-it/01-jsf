package org.beat.it.frontend.jsf;

import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.service.CartService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CartBean {

    @Inject
    private CartService cartService;

    public Integer getItems() {
        if (cartService.getCart() == null) {
            cartService.initCart();
        }
        return cartService.getCart().getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public Double getTotalPrice() {
        return cartService.getCart().getTotalPrice();
    }
}
