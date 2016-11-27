package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.PaymentMethod;
import org.beat.it.frontend.dto.cart.PaymentMethodDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class PaymentMethodTransformer {

    public List<PaymentMethodDTO> transform(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(this::transform).collect(Collectors.toList());
    }

    public PaymentMethodDTO transform(PaymentMethod paymentMethod) {
        return new PaymentMethodDTO(paymentMethod.getId(), paymentMethod.getPrice(), "EUR");
    }
}
