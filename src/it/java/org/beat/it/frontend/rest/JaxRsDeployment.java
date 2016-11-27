package org.beat.it.frontend.rest;

import org.beat.it.Main;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class JaxRsDeployment {

    public static Archive deployment() throws Exception {
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackages(true, "org.beat.it");
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
        deployment.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        deployment.addAllDependencies();
        return deployment;
    }
}
