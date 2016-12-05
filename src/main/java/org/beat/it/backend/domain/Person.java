package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {

    private String forename;
    private String surname;
    private String email;
    private String phone;

}
