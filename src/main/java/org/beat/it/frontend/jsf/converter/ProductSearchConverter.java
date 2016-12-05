package org.beat.it.frontend.jsf.converter;

import org.beat.it.backend.domain.Product;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.util.Map;

@FacesConverter("productSearchConverter")
public class ProductSearchConverter implements Converter {

    //inject thx to omnifaces since 1.6
    @Inject
    private Map<String, Product> productsMap;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            return productsMap.get(value);
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Product) object).getId());
        } else {
            return null;
        }
    }
}
