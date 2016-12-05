package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Payment;
import org.beat.it.frontend.dto.cart.PaymentDTO;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class PaymentTransformer {

    public PaymentDTO transform(Payment payment) {
        if (payment != null) {
            return new PaymentDTO(payment.getTotalPrice(), payment.getItemsPrice(),  "EUR", 
                    payment.getDeliveryPrice(), payment.getDeliveryType(), 
                    payment.getTransactionPrice(), payment.getPaymentMethod());
        } else return null;
    }
}
