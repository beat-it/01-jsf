package org.beat.it.frontend.transformer.catalog;

import org.beat.it.backend.domain.Product;
import org.beat.it.frontend.dto.catalog.ProductDTO;
import org.beat.it.frontend.transformer.ImageTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductTransformer {

    @Inject
    private ImageTransformer imageTransformer;

    public List<ProductDTO> transform(List<Product> products) {
        return products.stream().map(this::transform).collect(Collectors.toList());
    }

    public ProductDTO transform(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCurrency(),
                product.getDescription(), product.getRating(), imageTransformer.transform(product.getImage()));
    }
}
