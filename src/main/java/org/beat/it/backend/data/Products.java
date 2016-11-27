package org.beat.it.backend.data;

import org.beat.it.backend.domain.Image;
import org.beat.it.backend.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Petruna
 */
public class Products {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    static {
        PRODUCTS.add(new Product("1", "Volvo V40", 45435d, "EUR", "Nice car to ride in Sweden.", 3.14d, new Image("url1", "thumbnailUrl1", "catalogUrl1")));
        PRODUCTS.add(new Product("2", "Honda CR-V", 35435d, "EUR", "Nice car to ride on road.", 3.15d, new Image("url2", "thumbnailUrl2", "catalogUrl2")));
        PRODUCTS.add(new Product("3", "Mitsubishi Outlander", 25435d, "EUR", "Nice car to ride on dirty road.", 3.16d, new Image("url3", "thumbnailUrl2", "catalogUrl3")));
    }

    public static List<Product> all() {
        return PRODUCTS;
    }
}
