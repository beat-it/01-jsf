package org.beat.it.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.beat.it.backend.domain.Address;
import org.beat.it.backend.domain.BillingDetails;
import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.domain.DeliveryOption;
import org.beat.it.backend.domain.Payment;
import org.beat.it.backend.domain.PaymentMethod;
import org.beat.it.backend.domain.Person;
import org.beat.it.backend.repository.DeliveryOptionRepository;
import org.beat.it.backend.repository.PaymentMethodRepository;
import org.beat.it.backend.repository.ProductRepository;
import org.beat.it.backend.repository.SessionStorageRepository;
import org.beat.it.frontend.rest.authentication.TokenHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Martin Petruna
 */
@Slf4j
@ApplicationScoped
public class CartService {

    @Inject
    TokenHolder tokenHolder;
    @Inject
    SessionStorageRepository sessionStorageRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    DeliveryOptionRepository deliveryOptionRepository;
    @Inject
    PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> listPaymentMethods() {
        return paymentMethodRepository.listPaymentMethods();
    }

    public List<DeliveryOption> listDeliveryOptions() {
        return deliveryOptionRepository.listDeliveryOptions();
    }

    private void storeCart(Cart cart) {
        sessionStorageRepository.storeCart(getRepositoryKey(), cart);
    }

    private Cart retrieveCart() {
        return sessionStorageRepository.retrieveCart(getRepositoryKey());
    }

    public Cart processOrder(String deliveryType, String paymentMethod, Person person, BillingDetails billingDetails, Address address) {
        log.debug("About to process order for delivery type {}, payment method {}, person {}, billing details {} and address {}.",
                deliveryType, paymentMethod, person, billingDetails, address);
        Cart cart = sessionStorageRepository.retrieveCart(getRepositoryKey());
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cannot process order for nothing!");
        }
        log.debug("Processing order for cart {}.", cart);
        Double priceForDelivery = deliveryOptionRepository.deliveryOption(deliveryType).getPrice();
        log.debug("Price for delivery {} is {}.", deliveryType, priceForDelivery);
        Double priceForPayment = paymentMethodRepository.paymentMethod(paymentMethod).getPrice();
        log.debug("Price for payment {} is {}.", paymentMethod, priceForPayment);
        Payment payment = new Payment(cart.getItemsPrice() + priceForDelivery + priceForPayment, cart.getItemsPrice(), priceForDelivery, deliveryType, paymentMethod);
        cart.setPayment(payment);
        cart.setPerson(person);
        cart.setBillingDetails(billingDetails);
        cart.setAddress(address);
        log.debug("Cart looks like this {} after initialization.", cart);
        //creating new cart, ordered cart added to history
        sessionStorageRepository.storeCart(getRepositoryKey(), new Cart());
        sessionStorageRepository.storeHistoryCart(getRepositoryKey(), cart);
        return cart;
    }

    public Cart getCart() {
        return retrieveCart();
    }

    public void removeItemFromCart(String productId) {
        log.debug("About to remove item with productId {} from cart.", productId);
        Cart cart = sessionStorageRepository.retrieveCart(getRepositoryKey());
        if (cart != null) {
            Optional<CartItem> existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();
            if (existing.isPresent()) {
                log.debug("CartItem for productId {} is already present {}. Will remove it.", productId, existing.get());
                cart.getCartItems().remove(existing.get());
            }
        }
        log.debug("Cart now looks like this {}.", cart);
    }

    public void addItemToCart(String productId, Integer quantity) {
        log.debug("About to add item with productId {} with quantity {} to cart.", productId, quantity);
        Cart cart = sessionStorageRepository.retrieveCart(getRepositoryKey());
        if (cart == null) {
            cart = new Cart();
            storeCart(cart);
        }
        Optional<CartItem> existing = cart.getCartItems().stream().filter(item -> item.getProductId().equals(productId)).findFirst();
        if (existing.isPresent()) {
            log.debug("CartItem for productId {} is already present {}. Will increase quantity by {}.", productId, existing.get(), quantity);
            existing.get().add(quantity);
        } else {
            log.debug("CartItem for productId {} is not present. Will add with quantity {}.", productId, quantity);
            cart.getCartItems().add(new CartItem(productId, quantity, productRepository.listProductsAsMap().get(productId).getPrice()));
        }
        log.debug("Cart now looks like this {}.", cart);
    }

    private String getRepositoryKey(){
        return tokenHolder.getToken() != null ? tokenHolder.getToken() : getJsfKey();
    }

    private String getJsfKey() {
        String sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
        log.info("Jsf,  session id is {}", sessionId);
        return sessionId;
    }

    public void initCart(){
        storeCart(new Cart());
    }
}
