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
        PRODUCTS.add(new Product("1", "Volvo V40", 45435d, "Nice car to ride in Sweden.", 8.14d, new Image("/images/volvo.jpg", "/images/volvo.jpg", "/images/volvo.jpg")));
        PRODUCTS.add(new Product("2", "Honda CR-V", 35435d, "Nice car to ride on road.", 9.15d, new Image("/images/honda.jpg", "/images/honda.jpg", "/images/honda.jpg")));
        PRODUCTS.add(new Product("3", "Mitsubishi Outlander", 25435d, "Nice car to ride on dirty road.", 7.16d, new Image("/images/mitsubishi.jpg", "/images/mitsubishi.jpg", "/images/mitsubishi.jpg")));
        PRODUCTS.add(new Product("4", "BMW", 25435d, "Nice car to ride on dirty road.", 8.16d, new Image("/images/bmw.jpg", "/images/bmw.jpg", "/images/bmw.jpg")));
        PRODUCTS.add(new Product("5", "Audi", 25435d, "Nice car to ride on dirty road.", 6.17d, new Image("/images/audi.jpg", "/images/audi.jpg", "/images/audi.jpg")));
        PRODUCTS.add(new Product("6", "Skoda", 25435d, "Nice car to ride on dirty road.", 9.18d, new Image("/images/skoda.jpg", "/images/skoda.jpg", "/images/skoda.jpg")));
        PRODUCTS.add(new Product("7", "Ferrari", 25435d, "Nice car to ride on dirty road.", 5.19d, new Image("/images/ferrari.jpg", "/images/ferrari.jpg", "/images/ferrari.jpg")));
        PRODUCTS.add(new Product("8", "Seat", 23435d, "Nice car to ride on road.", 7.18d, new Image("/images/seat.jpg", "/images/seat.jpg", "/images/seat.jpg")));
    }

    public static List<Product> all() {
        return PRODUCTS;
    }
}
