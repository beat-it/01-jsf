package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Cart;
import org.beat.it.frontend.dto.cart.CartInfoDTO;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CartInfoTransformer {
    public CartInfoDTO transform(Cart cart) {
        return new CartInfoDTO(cart.getCartItems().size(), cart.getTotalPrice(), "EUR");
    }
}
