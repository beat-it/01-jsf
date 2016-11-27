package org.beat.it.frontend.jsf;

import org.beat.it.backend.service.CatalogService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class Message {

    @Inject
    private CatalogService catalogService;

    public String say() {
        return "Hello from BeatIT";
    }
}