package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.domain.Product;
import org.beat.it.frontend.dto.cart.CartItemDTO;
import org.beat.it.frontend.transformer.ImageTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CartItemTransformer {

    @Inject
    ImageTransformer imageTransformer;

    public List<CartItemDTO> transform(List<CartItem> cartItems, Map<String, Product> products) {
        List<CartItemDTO> collect = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDTO cartItemDTO = transform(cartItem, products.get(cartItem.getProductId()));
            collect.add(cartItemDTO);
        }
        return collect;
    }

    public CartItemDTO transform(CartItem cartItem, Product product) {
        return new CartItemDTO(product.getId(), product.getName(), imageTransformer.transform(product.getImage()), cartItem.getQuantity(), product.getPrice(), "EUR", cartItem.totalPrice());
    }
}
