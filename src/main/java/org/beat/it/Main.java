package org.beat.it;

import org.beat.it.frontend.jsf.Message;
import org.beat.it.frontend.rest.JaxRsApplication;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.cdi.CDIFraction;
import org.wildfly.swarm.jaxrs.JAXRSFraction;
import org.wildfly.swarm.jsf.JSFFraction;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * inspired by:
 * https://github.com/wildfly-swarm/wildfly-swarm-examples/tree/master/jsf/jsf-shrinkwrap
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Swarm swarm = new Swarm()
                .fraction(LoggingFraction.createDefaultLoggingFraction())
                .fraction(new JAXRSFraction())
                .fraction(new CDIFraction())
                .fraction(new JSFFraction());

        WARArchive deployment = ShrinkWrap.create(WARArchive.class);
        deployment.addClass(Message.class);
        deployment.addPackages(true, "org.beat.it");

        deployment.addAsWebResource(
                new ClassLoaderAsset("index.html", Main.class.getClassLoader()), "index.html");
        deployment.addAsWebResource(
                new ClassLoaderAsset("homepage.xhtml", Main.class.getClassLoader()), "homepage.xhtml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
        deployment.addAsWebResource("images/honda.jpg","honda.jpg");
        deployment.addAsWebResource("images/mitsubishi.jpg","mitsubishi.jpg");
        deployment.addAsWebResource("images/bmw.jpg","bmw.jpg");
        deployment.addAsWebResource("images/ferrari.jpg","ferrari.jpg");
        deployment.addAsWebResource("images/volvo.jpg","volvo.jpg");
        deployment.addAsWebResource("images/skoda.jpg","skoda.jpg");
        deployment.addAsWebResource("images/audi.jpg","audi.jpg");
        deployment.addAsWebResource("images/shopping.png","shopping.png");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/beans.xml", Main.class.getClassLoader()), "beans.xml");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/template.xhtml", Main.class.getClassLoader()), "template.xhtml");
        deployment.addAllDependencies();

        swarm.start().deploy(deployment);
    }
}