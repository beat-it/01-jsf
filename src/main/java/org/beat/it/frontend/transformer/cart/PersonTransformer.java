package org.beat.it.frontend.transformer.cart;

import org.beat.it.backend.domain.Person;
import org.beat.it.frontend.dto.cart.PersonDTO;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Martin Petruna
 */
@ApplicationScoped
public class PersonTransformer {

    public Person transform(PersonDTO person) {
        return person == null ? null
                : new Person(person.getForename(), person.getSurname(), person.getEmail(), person.getPhone());
    }

    public PersonDTO transform(Person person) {
        if (person != null) {
            return new PersonDTO(person.getForename(), person.getSurname(), person.getEmail(), person.getPhone());
        } else return null;
    }
}
