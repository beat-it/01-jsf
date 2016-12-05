package org.beat.it.frontend.jsf.validator;

import org.omnifaces.validator.MultiFieldValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class DifferentDeliveryAddressValidator implements MultiFieldValidator {

    @Override
    public boolean validateValues(FacesContext context, List<UIInput> components, List<Object> values) {
        if (values.get(0) != null && (Boolean) values.get(0)) {
            return !values.get(1).equals(values.get(5)) ||
                    !values.get(2).equals(values.get(6)) ||
                    !values.get(3).equals(values.get(7)) ||
                    !values.get(4).equals(values.get(8));
        } else {
            return true;
        }
    }
}
