package org.beat.it.backend.repository;

import org.beat.it.backend.data.PaymentMethods;
import org.beat.it.backend.domain.PaymentMethod;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class PaymentMethodRepository {

    public List<PaymentMethod> listPaymentMethods() {
        return PaymentMethods.all();
    }

    public PaymentMethod paymentMethod(String id) {
        Optional<PaymentMethod> first = PaymentMethods.all().stream().filter(paymentMethod -> paymentMethod.getId().equals(id)).findFirst();
        return first.isPresent() ? first.get() : null;
    }
}
