package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.service.CartService;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
@Slf4j
public class HomePageView {


    @Inject
    private CartService cartService;
    @Inject
    private List<Product> products;
    @Getter
    @Setter
    private List<Product> selectedProducts;

    public List<Product> getProductsForHomepage() {
        if (selectedProducts != null && !selectedProducts.isEmpty()) {
            return selectedProducts;
        } else {
            return products;
        }
    }

    public void buyAction(Product product) {
        cartService.addItemToCart(product.getId(), 1);
        log.info("buys...");
    }

    public List<Product> completeProduct(String query) {
        List<Product> filteredProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getName().toLowerCase().startsWith(query)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void onSearch(AjaxBehaviorEvent event) {
        log.info("Autocomplete search...");
    }


}
