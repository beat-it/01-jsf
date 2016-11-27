package org.beat.it.backend.service;

import org.beat.it.backend.domain.DeliveryOption;
import org.beat.it.backend.domain.PaymentMethod;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.domain.ProductSearchRequest;
import org.beat.it.backend.repository.DeliveryOptionRepository;
import org.beat.it.backend.repository.PaymentMethodRepository;
import org.beat.it.backend.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CatalogService {

    @Inject
    PaymentMethodRepository paymentMethodRepository;
    @Inject
    DeliveryOptionRepository deliveryOptionRepository;
    @Inject
    ProductRepository productRepository;

    public List<PaymentMethod> listPaymentMethods() {
        return paymentMethodRepository.listPaymentMethods();
    }

    public List<DeliveryOption> listDeliveryOptions() {
        return deliveryOptionRepository.listDeliveryOptions();
    }

    public List<Product> listProducts() {
        return productRepository.listProducts();
    }

    public List<Product> searchProducts(ProductSearchRequest productSearchRequest) {
       return productRepository.search(productSearchRequest);
    }
}
