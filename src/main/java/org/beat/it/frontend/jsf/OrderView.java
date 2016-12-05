package org.beat.it.frontend.jsf;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.beat.it.backend.domain.Address;
import org.beat.it.backend.domain.CartItem;
import org.beat.it.backend.domain.Company;
import org.beat.it.backend.domain.Person;
import org.beat.it.backend.service.CartService;
import org.primefaces.event.FlowEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
//http://stackoverflow.com/questions/20251176/viewscoped-bean-recreated-on-every-postback-request-when-using-jsf-2-2
@ViewScoped
@Getter
@Setter
@Slf4j
public class OrderView implements Serializable {

    @Inject
    private CartService cartService;

    private Person person = new Person();
    private Company company = new Company();
    private Address address = new Address();
    private Address deliveryAddress = new Address();

    private boolean deliveryAddressDifferent = false;
    private boolean asCompany = false;

    public void removeItemAction(CartItem cartItem) {
        cartService.removeItemFromCart(cartItem.getProductId());
        log.info("removing from cart...");
    }

    public String flowListener(FlowEvent event) {
        System.out.println("Flow Event Happened :: New Step :: " + event.getNewStep() + " :: Old Step :: " + event.getOldStep());
        return event.getNewStep();
    }

    public void captcha() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
