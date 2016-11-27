package org.beat.it.frontend.rest;

import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RunWith(Arquillian.class)
public class EndToEndShoppingITTest {

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
    public void test_shopping() {

        //TODO

        //1.homepage

        //2.search

        //3.add item

        //4. search

        //5.add item

        //6.add item

        //7.check if CartInfo is correct

        //8.remove item

        //9.perform order

        //10.check if new cart has been created

        //11.check if previous order is in history

        //12.always send token and check if it stays the same

    }
}
