package org.beat.it.frontend.jsf;

import org.beat.it.Main;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Arquillian.class)
public class SampleITTest  {

    @Deployment
    public static Archive deployment() throws Exception {
         WARArchive deployment = ShrinkWrap.create(WARArchive.class);
        deployment.addClass(Message.class);
        deployment.addAsWebResource(
                new ClassLoaderAsset("index.html", Main.class.getClassLoader()), "index.html");
        deployment.addAsWebResource(
                new ClassLoaderAsset("index.xhtml", Main.class.getClassLoader()), "index.xhtml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
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
        browser.navigate().to("http://localhost:8080/index.xhtml");
        assertThat(browser.getPageSource()).contains("WildFly Swarm Facelet");
        assertThat(browser.getPageSource()).contains("Hello from BeatIT");
        assertThat(browser.getPageSource()).contains("Powered by WildFly Swarm");
    }
}