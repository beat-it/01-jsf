package org.beat.it.frontend.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class ProductListDTO implements Serializable{

    private List<ProductDTO> products;
}
