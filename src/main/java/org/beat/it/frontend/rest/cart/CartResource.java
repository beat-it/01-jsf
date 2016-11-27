package org.beat.it.frontend.rest.cart;

import org.beat.it.backend.service.CartService;
import org.beat.it.backend.service.CatalogService;
import org.beat.it.frontend.dto.cart.AddItemDTO;
import org.beat.it.frontend.dto.cart.CartDTO;
import org.beat.it.frontend.dto.cart.CartInfoDTO;
import org.beat.it.frontend.dto.cart.CartOrderDTO;
import org.beat.it.frontend.dto.cart.DeliveryOptionDTO;
import org.beat.it.frontend.dto.cart.PaymentMethodDTO;
import org.beat.it.frontend.rest.authentication.AuthenticationToken;
import org.beat.it.frontend.transformer.cart.BillingDetailsTransformer;
import org.beat.it.frontend.transformer.cart.CartInfoTransformer;
import org.beat.it.frontend.transformer.cart.CartTransformer;
import org.beat.it.frontend.transformer.cart.DeliveryOptionTransfomer;
import org.beat.it.frontend.transformer.cart.PaymentMethodTransformer;
import org.beat.it.frontend.transformer.cart.PersonTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Martin Petruna
 */
@Path("/cart")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    DeliveryOptionTransfomer deliveryOptionTransfomer;
    @Inject
    PaymentMethodTransformer paymentMethodTransformer;
    @Inject
    PersonTransformer personTransformer;
    @Inject
    BillingDetailsTransformer billingDetailsTransformer;
    @Inject
    CartTransformer cartTransformer;
    @Inject
    CartInfoTransformer cartInfoTransformer;
    @Inject
    CartService cartService;
    @Inject
    CatalogService catalogService;

    @AuthenticationToken
    @PUT
    public Response cart(CartOrderDTO cartOrderDTO) {
        cartService.processOrder(cartOrderDTO.getDeliveryType(),
                cartOrderDTO.getPaymentMethod(),
                personTransformer.transform(cartOrderDTO.getPerson()),
                billingDetailsTransformer.transform(cartOrderDTO.getBillingAddress()));
        return Response.ok().build();
    }

    @AuthenticationToken
    @GET
    public CartDTO cart() {
        return cartTransformer.transform(cartService.getCart(), catalogService.listProductsPerProductId());
    }

    @AuthenticationToken
    @GET
    @Path("/info")
    public CartInfoDTO info() {
        return cartInfoTransformer.transform(cartService.getCart());
    }

    @AuthenticationToken
    @GET
    @Path("/delivery")
    public List<DeliveryOptionDTO> delivery() {
        return deliveryOptionTransfomer.transform(cartService.listDeliveryOptions());
    }

    @AuthenticationToken
    @GET
    @Path("/payment")
    public List<PaymentMethodDTO> payment() {
        return paymentMethodTransformer.transform(cartService.listPaymentMethods());
    }

    @AuthenticationToken
    @DELETE
    @Path("/items/{itemId}")
    public CartInfoDTO items(@PathParam("itemId") String itemId) {
        cartService.removeItemFromCart(itemId);
        return cartInfoTransformer.transform(cartService.getCart());
    }

    @AuthenticationToken
    @POST
    @Path("/items")
    public CartInfoDTO items(AddItemDTO addItemDTO) {
        cartService.addItemToCart(addItemDTO.getProductId(), addItemDTO.getQuantity());
        return cartInfoTransformer.transform(cartService.getCart());
    }
}
