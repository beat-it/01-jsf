package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class CartInfoDTO implements Serializable {

    private Integer count;
    private Double totalPrice;
    private String currency;
}
