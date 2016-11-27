package org.beat.it.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ImageDTO implements Serializable{

    private String url;
    private String thumbnailUrl;
    private String catalogUrl;

}
