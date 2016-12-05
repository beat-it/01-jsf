package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class PaymentDTO implements Serializable {

    private Double totalPrice;
    private Double itemsPrice;
    private String currency = "EUR";
    private Double deliveryPrice;
    private String deliveryType;
    private Double transactionPrice;
    private String paymentMethod;

}
