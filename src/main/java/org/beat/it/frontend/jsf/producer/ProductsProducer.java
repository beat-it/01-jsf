package org.beat.it.frontend.jsf.producer;

import org.beat.it.backend.domain.Product;
import org.beat.it.backend.service.CatalogService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductsProducer {

    @Inject
    private CatalogService catalogService;

    @Named
    @Produces
    public List<Product> getProducts() {
        return catalogService.listProducts();
    }

    @Named
    @Produces
    public Map<String, Product> getProductsMap(){
        return catalogService.listProducts().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
