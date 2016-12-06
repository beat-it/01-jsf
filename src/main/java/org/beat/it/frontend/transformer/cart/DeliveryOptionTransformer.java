package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.DeliveryOption;
import org.beat.it.frontend.dto.cart.DeliveryOptionDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class DeliveryOptionTransformer {

    public List<DeliveryOptionDTO> transform(List<DeliveryOption> deliveryOptions) {
        return deliveryOptions.stream().map(this::transform).collect(Collectors.toList());
    }

    public DeliveryOptionDTO transform(DeliveryOption deliveryOption) {
        return new DeliveryOptionDTO(deliveryOption.getId(), deliveryOption.getPrice(), "EUR", deliveryOption.getName());
    }
}
