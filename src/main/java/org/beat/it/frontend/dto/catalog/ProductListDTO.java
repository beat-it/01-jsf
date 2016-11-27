package org.beat.it.frontend.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Martin Petruna
 */
@Data
@AllArgsConstructor
public class ProductListDTO {

    private List<ProductDTO> products;
}
