package spring.eshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.eshopping.entities.users.Customer;
import spring.eshopping.entities.users.Seller;
import spring.eshopping.services.UserRegisterService;
import spring.eshopping.utils.SendEmail;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//USER REGISTRATION API
@RestController
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private SendEmail sendEmail;

    //API TO REGISTER CUSTOMER
    @PostMapping("register/customer")
    public String registerCustomer(@Valid @RequestBody Customer customer, HttpServletResponse httpServletResponse){
        String getMessage =userRegisterService.registerCustomer(customer);

        if(getMessage.equals("success"))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        }
        else
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

//API TO REGISTER SELLER
    @PostMapping("register/seller")
    public String registerSeller(@Valid @RequestBody Seller seller, HttpServletResponse httpServletResponse){
        String getMessage =userRegisterService.registerSeller(seller);
        if(getMessage.equals("success"))
        {
            sendEmail.sendEmail("Account Created","Waiting for approval",seller.getEmail());
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED );
        }
        else
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }
}
