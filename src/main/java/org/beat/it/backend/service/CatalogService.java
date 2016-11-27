package org.beat.it.backend.service;

import org.beat.it.backend.domain.Product;
import org.beat.it.backend.domain.ProductSearchRequest;
import org.beat.it.backend.repository.DeliveryOptionRepository;
import org.beat.it.backend.repository.PaymentMethodRepository;
import org.beat.it.backend.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CatalogService {

    @Inject
    PaymentMethodRepository paymentMethodRepository;
    @Inject
    DeliveryOptionRepository deliveryOptionRepository;
    @Inject
    ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.listProducts();
    }

    public List<Product> searchProducts(ProductSearchRequest productSearchRequest) {
        return productRepository.search(productSearchRequest);
    }

    public Map<String, Product> listProductsPerProductId() {
        return productRepository.listProductsAsMap();
    }
}
