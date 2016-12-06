package org.beat.it.backend.data;

import org.beat.it.backend.domain.DeliveryOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Petruna
 */
public class DeliveryOptions {

    private static final List<DeliveryOption> DELIVERY_OPTIONS = new ArrayList<>();

    static {
        DELIVERY_OPTIONS.add(new DeliveryOption("KURIER", 2.5d, "Kuriér SPS"));
        DELIVERY_OPTIONS.add(new DeliveryOption("OSOBNY_ODBER", 0d, "Osobný odber"));
    }

    public static List<DeliveryOption> all() {
        return DELIVERY_OPTIONS;
    }
}
