package org.beat.it.frontend.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.data.Offset;
import org.beat.it.frontend.dto.cart.AddItemDTO;
import org.beat.it.frontend.dto.cart.AddressDTO;
import org.beat.it.frontend.dto.cart.BillingDetailsDTO;
import org.beat.it.frontend.dto.cart.CartDTO;
import org.beat.it.frontend.dto.cart.CartInfoDTO;
import org.beat.it.frontend.dto.cart.CartItemDTO;
import org.beat.it.frontend.dto.cart.CartOrderDTO;
import org.beat.it.frontend.dto.cart.CompanyDTO;
import org.beat.it.frontend.dto.cart.DeliveryOptionDTO;
import org.beat.it.frontend.dto.cart.PaymentDTO;
import org.beat.it.frontend.dto.cart.PaymentMethodDTO;
import org.beat.it.frontend.dto.cart.PersonDTO;
import org.beat.it.frontend.dto.catalog.ProductDTO;
import org.beat.it.frontend.dto.catalog.ProductListDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.cdi.CDIFraction;
import org.wildfly.swarm.jaxrs.JAXRSFraction;
import org.wildfly.swarm.jsf.JSFFraction;
import org.wildfly.swarm.logging.LoggingFraction;

import javax.ws.rs.core.HttpHeaders;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@RunWith(Arquillian.class)
public class EndToEndShoppingITTest {

    @Deployment
    public static Archive deployment() throws Exception {
        return JaxRsDeployment.deployment();
    }

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm()
                .fraction(LoggingFraction.createDefaultLoggingFraction())
                .fraction(new JAXRSFraction())
                .fraction(new CDIFraction())
                .fraction(new JSFFraction());
    }

    @BeforeClass
    public static void before() {
        RestAssured.baseURI = "http://localhost:8080/service";
    }

    @RunAsClient
    @Test
    public void test_shopping() {

        //1. homepage

        Response response = given().log().all().when().get("/catalog/homepage").andReturn();
        response.then().log().all();
        ProductListDTO productListDTO = response.getBody().as(ProductListDTO.class);
        assertThat(productListDTO.getProducts().size()).isEqualTo(3);
        String token = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token).isNotNull();

        //2. search

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/catalog/search/V").thenReturn();
        response.then().log().all();
        ProductListDTO productListDTOSearch = response.getBody().as(ProductListDTO.class);
        assertThat(productListDTOSearch.getProducts().size()).isEqualTo(1);
        String token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //3. add item

        ProductDTO volvo = productListDTO.getProducts().get(0);
        AddItemDTO addItemDTO = new AddItemDTO(1, volvo.getProductId());
        response = given().log().all().body(addItemDTO).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).post("/cart/items").thenReturn();
        response.then().log().all();
        CartInfoDTO cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(1);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(volvo.getPrice(), Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //4. search

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/catalog/search/H").thenReturn();
        response.then().log().all();
        productListDTOSearch = response.getBody().as(ProductListDTO.class);
        assertThat(productListDTOSearch.getProducts().size()).isEqualTo(1);
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //5. add item

        ProductDTO honda = productListDTOSearch.getProducts().get(0);
        addItemDTO = new AddItemDTO(1, honda.getProductId());
        response = given().log().all().body(addItemDTO).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).post("/cart/items").thenReturn();
        response.then().log().all();
        cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(2);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(volvo.getPrice() + honda.getPrice(), Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //6. add item

        addItemDTO = new AddItemDTO(2, volvo.getProductId());
        response = given().log().all().body(addItemDTO).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).post("/cart/items").thenReturn();
        response.then().log().all();
        cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(4);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(3 * volvo.getPrice() + honda.getPrice(), Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //7. search

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/catalog/search/M").thenReturn();
        response.then().log().all();
        productListDTOSearch = response.getBody().as(ProductListDTO.class);
        assertThat(productListDTOSearch.getProducts().size()).isEqualTo(1);
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //8. add item

        ProductDTO mitsubishi = productListDTOSearch.getProducts().get(0);
        addItemDTO = new AddItemDTO(1, mitsubishi.getProductId());
        response = given().log().all().body(addItemDTO).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).post("/cart/items").thenReturn();
        response.then().log().all();
        cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(5);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(3 * volvo.getPrice() + honda.getPrice() + mitsubishi.getPrice(), Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //9. remove item

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).delete("/cart/items/{itemsId}", honda.getProductId()).thenReturn();
        response.then().log().all();
        cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(4);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(3 * volvo.getPrice() + mitsubishi.getPrice(), Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //10. get payment methods

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/cart/payment").andReturn();
        response.then().log().all();
        List<PaymentMethodDTO> paymentMethodDTOs = Arrays.asList(response.getBody().as(PaymentMethodDTO[].class));
        assertThat(paymentMethodDTOs.size()).isEqualTo(2);
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //11. get delivery options

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/cart/delivery").andReturn();
        response.then().log().all();
        List<DeliveryOptionDTO> deliveryOptionDTOs = Arrays.asList(response.getBody().as(DeliveryOptionDTO[].class));
        assertThat(deliveryOptionDTOs.size()).isEqualTo(2);
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //10. perform order

        String dobierka = paymentMethodDTOs.get(1).getId();
        String kurier = deliveryOptionDTOs.get(0).getId();

        PersonDTO personDTO = new PersonDTO("Martin", "Petruna", "dabrockij@gmail.com", "0907471133");

        AddressDTO addressDTO = new AddressDTO("Moldavska 10", "Kosice", "04011", "Slovakia");
        CompanyDTO companyDTO = new CompanyDTO("Prodium s.r.o.", "123", "456", "789");

        BillingDetailsDTO billingDetailsDTO = new BillingDetailsDTO(addressDTO, companyDTO);

        CartOrderDTO cartOrderDTO = new CartOrderDTO(kurier, dobierka, personDTO, billingDetailsDTO, addressDTO);

        response = given().log().all().body(cartOrderDTO).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).put("/cart").thenReturn();
        response.then().log().all();

        //11. check full cart

        CartDTO cartDTO = response.getBody().as(CartDTO.class);
        assertThat(cartDTO.getAddress()).isEqualTo(addressDTO);
        assertThat(cartDTO.getBillingDetails()).isEqualTo(billingDetailsDTO);
        assertThat(cartDTO.getPerson()).isEqualTo(personDTO);
        assertThat(cartDTO.getPayment()).isEqualTo(
                new PaymentDTO(3 * volvo.getPrice() + mitsubishi.getPrice() + paymentMethodDTOs.get(1).getPrice() + deliveryOptionDTOs.get(0).getPrice(),
                        3 * volvo.getPrice() + mitsubishi.getPrice(), deliveryOptionDTOs.get(0).getPrice(), "EUR", kurier, dobierka));
        assertThat(cartDTO.getCartItems().size()).isEqualTo(2);
        CartItemDTO volvoItem = cartDTO.getCartItems().get(0);
        CartItemDTO mitsubishiItem = cartDTO.getCartItems().get(1);
        assertThat(volvoItem.getQuantity()).isEqualTo(3);
        assertThat(mitsubishiItem.getQuantity()).isEqualTo(1);
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //12. check if new cart has been created

        response = given().log().all().when().header(HttpHeaders.AUTHORIZATION, token).get("/cart/info").andReturn();
        response.then().log().all();
        cartInfoDTO = response.getBody().as(CartInfoDTO.class);
        assertThat(cartInfoDTO.getCount()).isEqualTo(0);
        assertThat(cartInfoDTO.getCurrency()).isEqualTo("EUR");
        assertThat(cartInfoDTO.getTotalPrice()).isCloseTo(0, Offset.offset(0.000001d));
        token2 = response.getHeader(HttpHeaders.AUTHORIZATION);
        assertThat(token2).isEqualTo(token);

        //13. check if previous order is in history

        response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().header(HttpHeaders.AUTHORIZATION, token).get("/cart").andReturn();
        response.then().log().all();
        cartDTO = response.getBody().as(CartDTO.class);

        //TODO

        //TODO 2 test multiple parallel shoppings

    }
}
