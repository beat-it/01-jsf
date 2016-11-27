package org.beat.it.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Image {

    private String url;
    private String thumbnailUrl;
    private String catalogUrl;
}
