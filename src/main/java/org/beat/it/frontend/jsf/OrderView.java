package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.service.CartService;
import org.beat.it.backend.service.CatalogService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
//http://stackoverflow.com/questions/20251176/viewscoped-bean-recreated-on-every-postback-request-when-using-jsf-2-2
@ViewScoped
@Getter
@Setter
@Slf4j
public class OrderView implements Serializable{

    @Inject
    private CartService cartService;

    public void removeItemAction(CartItem cartItem) {
        cartService.removeItemFromCart(cartItem.getProductId());
        log.info("removing from cart...");
    }

}
