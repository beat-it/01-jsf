package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;
import org.beat.it.backend.domain.Product;
import org.beat.it.backend.service.CatalogService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
@Getter
@Setter
public class HomePageBean {

    @Inject
    private CatalogService catalogService;

    public List<Product> getProducts() {
        return catalogService.listProducts();
    }
}
