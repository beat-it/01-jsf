package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.service.CartService;
import org.beat.it.backend.service.CatalogService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
@Getter
@Setter
@Slf4j
public class HomePageView {


    @Inject
    private CartService cartService;

    public void buyAction(Product product) {
        cartService.addItemToCart(product.getId(), 1);
        log.info("buys...");
    }


}
