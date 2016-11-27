package org.beat.it.backend.service;

import org.beat.it.backend.data.DeliveryOptions;
import org.beat.it.backend.data.PaymentMethods;
import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.DeliveryOption;
import org.beat.it.backend.domain.PaymentMethod;
import org.beat.it.backend.repository.SessionStorageRepository;
import org.beat.it.frontend.rest.authentication.TokenHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class ShoppingService {

    @Inject
    TokenHolder tokenHolder;
    @Inject
    SessionStorageRepository sessionStorageRepository;

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

}
