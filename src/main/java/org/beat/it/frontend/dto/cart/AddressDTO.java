package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AddressDTO implements Serializable {

    private String street;
    private String city;
    private String zip;
    private String country;

}
