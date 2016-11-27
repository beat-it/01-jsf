package org.beat.it.backend.repository;

import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.domain.Order;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class SessionStorageRepository {

    private final ConcurrentMap<String, Cart> state = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, List<Order>> history = new ConcurrentHashMap<>();

    public void storeCart(String key, Cart cart) {
        state.put(key, cart);
    }

    public Cart retrieveCart(String key) {
        return state.get(key);
    }

    public void storeHistoryCart(String token, Cart cart) {
        if (!history.keySet().contains(token)) {
            history.put(token, new ArrayList<>());
        }
        history.get(token).add(new Order(cart));
    }
}
