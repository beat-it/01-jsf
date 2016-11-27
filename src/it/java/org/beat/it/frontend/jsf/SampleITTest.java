package org.beat.it.frontend.jsf;

import lombok.extern.slf4j.Slf4j;
import org.beat.it.Main;
import org.beat.it.frontend.rest.JaxRsApplication;
import org.beat.it.frontend.rest.cart.CartResource;
import org.beat.it.frontend.rest.catalog.CatalogResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.undertow.WARArchive;

import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Martin Petruna
 */
@Slf4j
@RunWith(Arquillian.class)
public class SampleITTest  {

//    @ArquillianResource
//    URL deploymentUrl;

    @Deployment
    public static Archive deployment() throws Exception {
         WARArchive deployment = ShrinkWrap.create(WARArchive.class);
        deployment.addClass(Message.class);
        deployment.addPackages(true, "org.beat.it");

        /**
         * NOTE: I need to add my app with custom path, otherwise default app with "/" will collide with jsf
         *
         * https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/content/common/jax-rs.html
         *
         * Additionally, the JAXRSArchive can provide a default Application with the @ApplicationPath annotation if you do not provide one when creating the deployment. If you do provide an Application it will take precedence.
         If you are building a .war-based application, you must provide the Application implementation with the appropriate @ApplicationPath annotation.
         */
        deployment.addClass(JaxRsApplication.class);;

        deployment.addAsWebResource(
                new ClassLoaderAsset("index.html", Main.class.getClassLoader()), "index.html");
        deployment.addAsWebResource(
                new ClassLoaderAsset("index.xhtml", Main.class.getClassLoader()), "index.xhtml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/beans.xml", Main.class.getClassLoader()), "beans.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/template.xhtml", Main.class.getClassLoader()), "template.xhtml");
        deployment.addAllDependencies();
        return deployment;
    }

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm().fraction(LoggingFraction.createDebugLoggingFraction());
    }

    @Drone
    WebDriver browser;

    //http://stackoverflow.com/questions/25189281/classnotfoundexception-org-openqa-selenium-webdriver
    @RunAsClient
    @Test
    public void testIt() {
        //log.info("deployment url "+deploymentUrl);

        browser.navigate().to("http://localhost:8080/index.xhtml");
        assertThat(browser.getPageSource()).contains("WildFly Swarm Facelet");
        assertThat(browser.getPageSource()).contains("Hello from BeatIT");
        assertThat(browser.getPageSource()).contains("Powered by WildFly Swarm");
    }
}