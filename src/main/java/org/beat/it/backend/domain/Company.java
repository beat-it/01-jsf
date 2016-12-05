package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Martin Petruna
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Company {

    private String name;
    private String dic;
    private String ico;
    private String icDph;
}
