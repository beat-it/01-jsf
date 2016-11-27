package org.beat.it.frontend.rest.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;

/**
 * @author Martin Petruna
 */
@RequestScoped
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TokenHolder {

    private String token;

}
