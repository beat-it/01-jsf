package org.beat.it.backend.service;

import org.beat.it.backend.data.DeliveryOptions;
import org.beat.it.backend.data.PaymentMethods;
import org.beat.it.backend.domain.BillingDetails;
import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.domain.DeliveryOption;
import org.beat.it.backend.domain.Payment;
import org.beat.it.backend.domain.PaymentMethod;
import org.beat.it.backend.domain.Person;
import org.beat.it.backend.repository.ProductRepository;
import org.beat.it.backend.repository.SessionStorageRepository;
import org.beat.it.frontend.rest.authentication.TokenHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CartService {

    @Inject
    TokenHolder tokenHolder;
    @Inject
    SessionStorageRepository sessionStorageRepository;
    @Inject
    ProductRepository productRepository;

    public List<PaymentMethod> listPaymentMethods() {
        return PaymentMethods.all();
    }

    public List<DeliveryOption> listDeliveryOptions() {
        return DeliveryOptions.all();
    }

    private void storeCart(Cart cart) {
        sessionStorageRepository.storeCart(tokenHolder.getToken(), cart);
    }

    private Cart retrieveCart() {
        return sessionStorageRepository.retrieveCart(tokenHolder.getToken());
    }

    public void processOrder(String deliveryType, String paymentMethod, Person person, BillingDetails billingDetails) {
        Cart cart = sessionStorageRepository.retrieveCart(tokenHolder.getToken());
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cannot process order for nothing!");
        }
        Payment payment = new Payment(cart.getTotalPrice(), cart.getItemsPrice(), cart.getPayment().getDeliveryPrice(), deliveryType, paymentMethod);
        cart.setPayment(payment);
        cart.setPerson(person);
        cart.setBillingDetails(billingDetails);
        //creating new cart, ordered cart added to history
        sessionStorageRepository.storeCart(tokenHolder.getToken(), new Cart());
        sessionStorageRepository.storeHistoryCart(tokenHolder.getToken(), cart);
    }

    public Cart getCart() {
        return retrieveCart();
    }

    public void removeItemFromCart(String productId) {
        Cart cart = sessionStorageRepository.retrieveCart(tokenHolder.getToken());
        if (cart != null) {
            Optional<CartItem> existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();
            if (existing.isPresent()) {
                cart.getCartItems().remove(existing.get());
            }
        }
    }

    public void addItemToCart(String productId, Integer quantity) {
        Cart cart = sessionStorageRepository.retrieveCart(tokenHolder.getToken());
        if (cart == null) {
            cart = new Cart();
            storeCart(cart);
        }
        Optional<CartItem> existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();
        if (existing.isPresent()) {
            existing.get().add(quantity);
        } else {
            cart.getCartItems().add(new CartItem(productId, quantity, productRepository.listProductsAsMap().get(productId).getPrice()));
        }
    }
}
