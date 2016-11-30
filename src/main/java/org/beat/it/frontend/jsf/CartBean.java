package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
@Getter
@Setter
public class CartBean {

    private Integer items = 3;
    private Double totalPrice = 22.58;

}
