package org.beat.it.backend.repository;

import org.beat.it.backend.data.Products;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.domain.ProductSearchRequest;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class ProductRepository {

    public List<Product> listProducts() {
        return Products.all();
    }

    public List<Product> search(ProductSearchRequest productSearchRequest) {
        return Products.all().stream().filter(product -> product.getName().startsWith(productSearchRequest.getName())).collect(Collectors.toList());
    }

    //TODO cache
    public Map<String, Product> listProductsAsMap() {
        return listProducts().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
