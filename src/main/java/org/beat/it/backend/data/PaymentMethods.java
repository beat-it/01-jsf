package org.beat.it.backend.data;

import org.beat.it.backend.domain.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Petruna
 */
public class PaymentMethods {

    private static final List<PaymentMethod> PAYMENT_METHODS = new ArrayList<>();

    static {
        PAYMENT_METHODS.add(new PaymentMethod("CARD_ONLINE", 0d, "Kartou online"));
        PAYMENT_METHODS.add(new PaymentMethod("DOBIERKA", 1.5d, "Dobierka"));
    }

    public static List<PaymentMethod> all() {
        return PAYMENT_METHODS;
    }
}
