package org.beat.it.jsf;

import javax.enterprise.inject.Model;

@Model
public class Message {
    public String say() {
        return "Hello from BeatIT";
    }
}