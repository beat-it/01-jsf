package org.beat.it.backend.repository;

import org.beat.it.backend.domain.Cart;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class SessionStorageRepository {

    private final ConcurrentMap<String, Cart> state = new ConcurrentHashMap<>();

    public void storeCart(String key, Cart cart) {
        state.put(key, cart);
    }

    public Cart retrieveCart(String key) {
        return state.get(key);
    }

}
