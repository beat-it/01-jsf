package org.beat.it.frontend.rest;

import lombok.extern.slf4j.Slf4j;
import org.beat.it.Main;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.cdi.CDIFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jaxrs.JAXRSFraction;
import org.wildfly.swarm.jsf.JSFFraction;
import org.wildfly.swarm.logging.LoggingFraction;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@RunWith(Arquillian.class)
public class CatalogResourceITTest {

    @Deployment
    public static Archive deployment() throws Exception {
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackages(true, "org.beat.it");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
        deployment.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        deployment.addAllDependencies();
        return deployment;
    }

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm()
                .fraction(LoggingFraction.createDebugLoggingFraction())
                .fraction(new JAXRSFraction())
                .fraction(new CDIFraction())
                .fraction(new JSFFraction());
    }

    @Drone
    WebDriver browser;

    @RunAsClient
    @Test
    public void test_homepage() {
        browser.navigate().to("http://localhost:8080/service/catalog/homepage");
        log.info(browser.getPageSource());
        assertThat(browser.getPageSource()).contains("[{\"productId\":\"1\",\"name\":\"Volvo V40\",\"price\":45435.0," +
                "\"currency\":\"EUR\",\"description\":\"Nice car to ride in Sweden.\",\"rating\":3.14,\"image\":{\"url\"" +
                ":\"url1\",\"thumbnailUrl\":\"thumbnailUrl1\",\"catalogUrl\":\"catalogUrl1\"}},{\"productId\":\"2\",\"name" +
                "\":\"Honda CR-V\",\"price\":35435.0,\"currency\":\"EUR\",\"description\":\"Nice car to ride on road.\"," +
                "\"rating\":3.15,\"image\":{\"url\":\"url2\",\"thumbnailUrl\":\"thumbnailUrl2\",\"catalogUrl\":" +
                "\"catalogUrl2\"}},{\"productId\":\"3\",\"name\":\"Mitsubishi Outlander\",\"price\":25435.0,\"currency\"" +
                ":\"EUR\",\"description\":\"Nice car to ride on dirty road.\",\"rating\":3.16,\"image\":{\"url\":\"url3\"" +
                ",\"thumbnailUrl\":\"thumbnailUrl2\",\"catalogUrl\":\"catalogUrl3\"}}]");
    }
}
