package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.BillingDetails;
import org.beat.it.frontend.dto.cart.BillingDetailsDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class BillingDetailsTransformer {

    @Inject
    AddressTransformer addressTransformer;
    @Inject
    CompanyTransformer companyTransformer;

    public BillingDetails transform(BillingDetailsDTO billingAddress) {
        return billingAddress == null ? null
                : new BillingDetails(addressTransformer.transform(billingAddress.getBillingAddress())
                , companyTransformer.transform(billingAddress.getCompany()));
    }

    public BillingDetailsDTO transform(BillingDetails billingDetails) {
        if (billingDetails != null) {
            return new BillingDetailsDTO(addressTransformer.transform(billingDetails.getBillingAddress())
                    , companyTransformer.transform(billingDetails.getCompany()));
        } else {
            return null;
        }
    }
}
