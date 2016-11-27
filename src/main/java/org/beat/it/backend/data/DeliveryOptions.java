package org.beat.it.backend.data;

import org.beat.it.backend.domain.DeliveryOption;

import java.util.ArrayList;
import java.util.List;

public class DeliveryOptions {

    private static final List<DeliveryOption> DELIVERY_OPTIONS = new ArrayList<>();

    static {
        DELIVERY_OPTIONS.add(new DeliveryOption("KURIER", 2.5d, "EUR"));
        DELIVERY_OPTIONS.add(new DeliveryOption("OSOBNY_ODBER", 0d, "EUR"));
    }

    public static List<DeliveryOption> all() {
        return DELIVERY_OPTIONS;
    }
}
