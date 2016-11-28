package org.beat.it;

import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.CartItem;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StreamTest {

    @Test
    public void test() {
        Cart cart = new Cart();
        CartItem cartItem1 = new CartItem("1", 2, 2.4);
        CartItem cartItem2 = new CartItem("2", 1, 3.4);
        CartItem cartItem3 = new CartItem("3", 3, 5.4);
        cart.getCartItems().add(cartItem1);
        cart.getCartItems().add(cartItem2);
        cart.getCartItems().add(cartItem3);
        Optional<CartItem> existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals("2")).findFirst();
        assertThat(existing.isPresent()).isTrue();
        assertThat(existing.get().getQuantity()).isEqualTo(1);

        cart = new Cart();
        existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals("2")).findFirst();
        assertThat(existing.isPresent()).isFalse();
    }
}
