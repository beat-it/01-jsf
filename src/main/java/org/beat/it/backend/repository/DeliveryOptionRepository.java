package org.beat.it.backend.repository;

import org.beat.it.backend.data.DeliveryOptions;
import org.beat.it.backend.domain.DeliveryOption;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class DeliveryOptionRepository {

    public List<DeliveryOption> listDeliveryOptions() {
        return DeliveryOptions.all();
    }
}
