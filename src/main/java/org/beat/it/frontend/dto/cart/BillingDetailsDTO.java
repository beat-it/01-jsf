package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BillingDetailsDTO implements Serializable {

    private AddressDTO billingAddress;
    private CompanyDTO company;
}
