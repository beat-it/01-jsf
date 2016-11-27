package org.beat.it.frontend.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.beat.it.frontend.dto.cart.DeliveryOptionDTO;
import org.beat.it.frontend.dto.cart.PaymentMethodDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.cdi.CDIFraction;
import org.wildfly.swarm.jaxrs.JAXRSFraction;
import org.wildfly.swarm.jsf.JSFFraction;
import org.wildfly.swarm.logging.LoggingFraction;

import javax.ws.rs.core.HttpHeaders;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Martin Petruna
 */
@Slf4j
@RunWith(Arquillian.class)
public class CartResourceITTest {

    @Deployment
    public static Archive deployment() throws Exception {
        return JaxRsDeployment.deployment();
    }

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm()
                .fraction(LoggingFraction.createDebugLoggingFraction())
                .fraction(new JAXRSFraction())
                .fraction(new CDIFraction())
                .fraction(new JSFFraction());
    }

    @BeforeClass
    public static void before() {
        RestAssured.baseURI = "http://localhost:8080/service";
    }

    @RunAsClient
    @Test
    public void test_delivery() {
        Response response = given().when().get("/cart/delivery").andReturn();
        List<DeliveryOptionDTO> deliveryOptionDTOs = Arrays.asList(response.getBody().as(DeliveryOptionDTO[].class));
        assertThat(deliveryOptionDTOs.size()).isEqualTo(2);
        String token1 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token1).isNotNull();
    }

    @RunAsClient
    @Test
    public void test_payment() {
        Response response = given().when().get("/cart/payment").andReturn();
        List<PaymentMethodDTO> paymentMethodDTOs = Arrays.asList(response.getBody().as(PaymentMethodDTO[].class));
        assertThat(paymentMethodDTOs.size()).isEqualTo(2);
        String token1 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token1).isNotNull();
    }
}
