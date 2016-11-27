package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.Product;
import org.beat.it.frontend.dto.cart.CartDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CartTransformer {

    @Inject
    CartItemTransformer cartItemTransformer;
    @Inject
    PaymentTransformer paymentTransformer;
    @Inject
    PersonTransformer personTransformer;
    @Inject
    BillingDetailsTransformer billingDetailsTransformer;
    @Inject
    AddressTransformer addressTransformer;

    public CartDTO transform(Cart cart, Map<String, Product> products) {
        return new CartDTO(cartItemTransformer.transform(cart.getCartItems(), products),
                paymentTransformer.transform(cart.getPayment()),
                personTransformer.transform(cart.getPerson()),
                billingDetailsTransformer.transform(cart.getBillingDetails()),
                addressTransformer.transform(cart.getAddress())
        );
    }
}
