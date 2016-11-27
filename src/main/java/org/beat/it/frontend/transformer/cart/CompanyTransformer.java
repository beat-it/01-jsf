package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Company;
import org.beat.it.frontend.dto.cart.CompanyDTO;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class CompanyTransformer {

    public Company transform(CompanyDTO company) {
        return new Company(company.getName(), company.getDic(), company.getIco(), company.getIcDph());
    }

    public CompanyDTO transform(Company company) {
        return new CompanyDTO(company.getName(), company.getDic(), company.getIco(), company.getIcDph());
    }
}
