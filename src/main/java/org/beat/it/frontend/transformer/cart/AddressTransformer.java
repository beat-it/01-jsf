package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Address;
import org.beat.it.frontend.dto.cart.AddressDTO;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class AddressTransformer {

    public Address transform(AddressDTO addressDTO) {
        return new Address(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getZip(), addressDTO.getCountry());
    }

    public AddressDTO transform(Address address) {
        if (address != null) {
            return new AddressDTO(address.getStreet(), address.getCity(), address.getZip(), address.getCountry());
        } else {
            return null;
        }
    }
}
