package org.beat.it.backend.repository;

import org.beat.it.backend.data.DeliveryOptions;
import org.beat.it.backend.domain.DeliveryOption;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class DeliveryOptionRepository {

    public List<DeliveryOption> listDeliveryOptions() {
        return DeliveryOptions.all();
    }

    public DeliveryOption deliveryOption(String id) {
        Optional<DeliveryOption> first = DeliveryOptions.all().stream().filter(deliveryOption -> deliveryOption.getId().equals(id)).findFirst();
        return first.isPresent() ? first.get() : null;
    }
}
