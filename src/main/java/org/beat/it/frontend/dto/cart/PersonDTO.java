package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class PersonDTO implements Serializable{

    private String forename;
    private String surname;
    private String email;
    private String phone;
}
