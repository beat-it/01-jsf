package org.beat.it.frontend.transformer;

import org.beat.it.backend.domain.Image;
import org.beat.it.frontend.dto.ImageDTO;

public class ImageTransformer {

    public ImageDTO transform(Image image) {
        return new ImageDTO(image.getUrl(), image.getThumbnailUrl(), image.getCatalogUrl());
    }
}
