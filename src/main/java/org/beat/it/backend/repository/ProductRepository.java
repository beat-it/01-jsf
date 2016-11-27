package org.beat.it.backend.repository;

import org.beat.it.backend.data.Products;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.domain.ProductSearchRequest;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductRepository {

    public List<Product> listProducts() {
        return Products.all();
    }

    public List<Product> search(ProductSearchRequest productSearchRequest) {
        return Products.all().stream().filter(product -> product.getName().startsWith(productSearchRequest.getName())).collect(Collectors.toList());
    }
}
