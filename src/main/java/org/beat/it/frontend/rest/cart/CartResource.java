package org.beat.it.frontend.rest.cart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beat.it.backend.domain.Cart;
import org.beat.it.backend.service.CartService;
import org.beat.it.backend.service.CatalogService;
import org.beat.it.frontend.dto.cart.AddItemDTO;
import org.beat.it.frontend.dto.cart.CartDTO;
import org.beat.it.frontend.dto.cart.CartInfoDTO;
import org.beat.it.frontend.dto.cart.CartOrderDTO;
import org.beat.it.frontend.dto.cart.DeliveryOptionDTO;
import org.beat.it.frontend.dto.cart.PaymentMethodDTO;
import org.beat.it.frontend.rest.authentication.AuthenticationToken;
import org.beat.it.frontend.transformer.cart.AddressTransformer;
import org.beat.it.frontend.transformer.cart.BillingDetailsTransformer;
import org.beat.it.frontend.transformer.cart.CartInfoTransformer;
import org.beat.it.frontend.transformer.cart.CartTransformer;
import org.beat.it.frontend.transformer.cart.DeliveryOptionTransformer;
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
import org.beat.it.frontend.dto.cart.PaymentDTO;

/**
 * @author Martin Petruna
 */
@Path("/cart")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api
public class CartResource {

    @Inject
    DeliveryOptionTransformer deliveryOptionTransformer;
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
    AddressTransformer addressTransformer;
    @Inject
    CartService cartService;
    @Inject
    CatalogService catalogService;

    @AuthenticationToken
    @PUT
    @ApiOperation("Set parameters of an to-be-ordered cart.")
    public CartDTO modifyCart(CartOrderDTO cartOrderDTO) {
        Cart cart = cartService.updateCart(cartOrderDTO.getDeliveryType(),
                cartOrderDTO.getPaymentMethod(),
                personTransformer.transform(cartOrderDTO.getPerson()),
                billingDetailsTransformer.transform(cartOrderDTO.getBillingAddress()),
                addressTransformer.transform(cartOrderDTO.getAddress()));
        return cartTransformer.transform(cart, catalogService.listProductsPerProductId());
    }
    
    @AuthenticationToken
    @POST
    @Path("order")
    @ApiOperation("Confirm order")
    public CartDTO confirmOrder() {
        Cart cart = cartService.processOrder();
        return cartTransformer.transform(cart, catalogService.listProductsPerProductId());
    }

    @AuthenticationToken
    @GET
    @ApiOperation("Reads the contents of cart")
    public CartDTO loadCart() {
        return cartTransformer.transform(cartService.getCart(), catalogService.listProductsPerProductId());
    }

    @AuthenticationToken
    @GET
    @Path("/info")
    @ApiOperation("Gets cart summary info")
    public CartInfoDTO info() {
        return cartInfoTransformer.transform(cartService.getCart());
    }

    @AuthenticationToken
    @GET
    @Path("/delivery")
    @ApiOperation(value="Lists delivery options", responseContainer = "List", response = DeliveryOptionDTO.class)
    public List<DeliveryOptionDTO> deliveryOptions() {
        return deliveryOptionTransformer.transform(cartService.listDeliveryOptions());
    }
    
    @AuthenticationToken
    @GET
    @Path("/payment")
    @ApiOperation(value="Lists payment options", responseContainer = "List", response = PaymentMethodDTO.class)
    public List<PaymentMethodDTO> paymentOptions() {
        return paymentMethodTransformer.transform(cartService.listPaymentMethods());
    }

    @AuthenticationToken
    @DELETE
    @Path("/items/{itemId}")
    @ApiOperation("Delete an item from a cart")
    public CartInfoDTO deleteItem(@PathParam("itemId") String itemId) {
        cartService.removeItemFromCart(itemId);
        return cartInfoTransformer.transform(cartService.getCart());
    }

    @AuthenticationToken
    @POST
    @Path("/items")
    @ApiOperation("Add an item of a cart. Change quantity of an existing item.")
    public CartInfoDTO addItem(AddItemDTO addItemDTO) {
        cartService.addItemToCart(addItemDTO.getProductId(), addItemDTO.getQuantity());
        return cartInfoTransformer.transform(cartService.getCart());
    }
}
