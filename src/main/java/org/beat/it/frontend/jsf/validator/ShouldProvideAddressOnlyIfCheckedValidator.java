package org.beat.it.frontend.jsf.validator;

import org.omnifaces.validator.MultiFieldValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class ShouldProvideAddressOnlyIfCheckedValidator implements MultiFieldValidator {

    @Override
    public boolean validateValues(FacesContext context, List<UIInput> components, List<Object> values) {
        return (values.get(0) != null && (Boolean) values.get(0))
                ^ (values.get(1).equals("") && values.get(2).equals("") && values.get(3).equals("") && values.get(4).equals(""));
    }
}
