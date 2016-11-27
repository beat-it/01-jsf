package org.beat.it.backend.data;

import org.beat.it.backend.domain.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethods {

    private static final List<PaymentMethod> PAYMENT_METHODS = new ArrayList<>();

    static {
        PAYMENT_METHODS.add(new PaymentMethod("CARD_ONLINE", 0d, "EUR"));
        PAYMENT_METHODS.add(new PaymentMethod("DOBIERKA", 1.5d, "EUR"));
    }

    public static List<PaymentMethod> all() {
        return PAYMENT_METHODS;
    }
}
