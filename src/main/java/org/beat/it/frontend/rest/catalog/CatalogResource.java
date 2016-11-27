package org.beat.it.frontend.rest.catalog;

import org.beat.it.backend.domain.ProductSearchRequest;
import org.beat.it.backend.service.CatalogService;
import org.beat.it.frontend.dto.catalog.ProductDTO;
import org.beat.it.frontend.rest.authentication.AuthenticationToken;
import org.beat.it.frontend.transformer.catalog.ProductTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Martin Petruna
 */
@Path("/catalog")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @Inject
    private CatalogService catalogService;

    @Inject
    private ProductTransformer productTransformer;

    @AuthenticationToken
    @GET
    @Path("/homepage")
    public List<ProductDTO> homepage() {
        return productTransformer.transform(catalogService.listProducts());
    }

    @AuthenticationToken
    @GET
    @Path("/search/{name}")
    public List<ProductDTO> search(@PathParam("name") String name) {
        return productTransformer.transform(catalogService.searchProducts(new ProductSearchRequest(name)));
    }
}
