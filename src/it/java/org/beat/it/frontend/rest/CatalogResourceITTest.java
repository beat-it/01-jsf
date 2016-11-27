package org.beat.it.frontend.rest;

import lombok.extern.slf4j.Slf4j;
import org.beat.it.frontend.rest.catalog.CatalogResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@RunWith(Arquillian.class)
public class CatalogResourceITTest {

    @Deployment
    public static Archive deployment() throws Exception {
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        //deployment.addClass(CatalogResource.class);
        deployment.addPackage("org.beat.it");
        deployment.addAllDependencies();
        return deployment;
    }

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm().fraction(LoggingFraction.createDebugLoggingFraction());
    }

    @Drone
    WebDriver browser;

    @RunAsClient
    @Test
    public void test_homepage() {
        browser.navigate().to("http://localhost:8080/service/catalog/homepage");
        log.info(browser.getPageSource());
        //assertThat(browser.getPageSource()).contains("This is our exception page!");
    }
}
