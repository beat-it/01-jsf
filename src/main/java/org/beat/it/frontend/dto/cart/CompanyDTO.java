package org.beat.it.frontend.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class CompanyDTO implements Serializable{

    private String name;
    private String dic;
    private String ico;
    private String icDph;
}
