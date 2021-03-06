package org.beat.it.frontend.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.beat.it.frontend.dto.ImageDTO;

import java.io.Serializable;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private String productId;
    private String name;
    private Double price;
    private String currency = "EUR";
    private String description;
    private Double rating;
    private ImageDTO image;
}
