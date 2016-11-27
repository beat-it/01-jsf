package org.beat.it.frontend.rest.authentication;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Martin Petruna
 */
@Slf4j
@ApplicationScoped
public class TokenGenerator {

    public String generateToken() {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        log.debug("Token generated: " + token);
        return token;
    }

}
