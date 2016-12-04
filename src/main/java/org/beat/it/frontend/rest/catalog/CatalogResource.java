package org.beat.it.frontend.rest.catalog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beat.it.backend.domain.ProductSearchRequest;
import org.beat.it.backend.service.CatalogService;
import org.beat.it.frontend.dto.catalog.ProductListDTO;
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

/**
 * @author Martin Petruna
 */
@Path("/catalog")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api()
public class CatalogResource {

    @Inject
    private CatalogService catalogService;

    @Inject
    private ProductTransformer productTransformer;

    @AuthenticationToken
    @GET
    @Path("/homepage")
    @ApiOperation(value = "List all products for homepage", notes = "all products", response = ProductListDTO.class)
    public ProductListDTO homepage() {
        return new ProductListDTO(productTransformer.transform(catalogService.listProducts()));
    }

    @AuthenticationToken
    @GET
    @Path("/search/{name}")
    @ApiOperation(value = "List all products using name param to do prefix query", notes = "for autocomplete feature", response = ProductListDTO.class)
    public ProductListDTO search(@PathParam("name") String name) {
        return new ProductListDTO(productTransformer.transform(catalogService.searchProducts(new ProductSearchRequest(name))));
    }
}
