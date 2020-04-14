package spring.eshopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.eshopping.entities.users.CustomerActivate;
import spring.eshopping.entities.users.User;
import spring.eshopping.repositories.CustomerActivateRepo;
import spring.eshopping.repositories.UserRepo;
import spring.eshopping.utils.SendEmail;
import spring.eshopping.utils.ValidEmail;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class CustomerActivateService {
    @Autowired
    ValidEmail validEmail;

    @Autowired
    CustomerActivateRepo customerActivateRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SendEmail sendEmail;

    @Transactional
    //TO ACTIVATE CUSTOMER
    public String activateCustomer(String token) {
        CustomerActivate customerActivate = customerActivateRepo.findByToken(token);
        try {
            if (customerActivate.getToken().equals(null)) {
                return "invalid token";
            }
        } catch (NullPointerException ex) {
            return "invalid token";
        }
        Date date = new Date();
        long diff = date.getTime() - customerActivate.getExpiryDate().getTime();
        long diffHours = diff / (60 * 60 * 1000);
        // TOKEN EXPIRE CASE
        if (diffHours > 24) {
            customerActivateRepo.deleteByUserEmail(customerActivate.getUserEmail());

            String newToken = UUID.randomUUID().toString();

            CustomerActivate localCustomerActivate = new CustomerActivate();
            localCustomerActivate.setToken(newToken);
            localCustomerActivate.setUserEmail(customerActivate.getUserEmail());
            localCustomerActivate.setExpiryDate(new Date());

            customerActivateRepo.save(localCustomerActivate);

            sendEmail.sendEmail("RE-ACCOUNT ACTIVATE TOKEN", "http://localhost:8080/customer/activate/"+newToken, customerActivate.getUserEmail());

            return "Token has expired";
        }
        if (customerActivate.getToken().equals(token)) {
            User user = userRepo.findByEmail(customerActivate.getUserEmail());
            user.setActive(true);
            userRepo.save(user);
            sendEmail.sendEmail("ACCOUNT ACTIVATED", "Your account has been activated", customerActivate.getUserEmail());
            customerActivateRepo.deleteByUserEmail(customerActivate.getUserEmail());
            return "Success";
        }

        return "Success";
    }

    @Transactional
    //TO RE-SEND ACTIVATION LINK
    public String resendLink(String email) {
        if (!validEmail.checkEmailValid(email)) {
            return "email is invalid";
        }
        User user = userRepo.findByEmail(email);
        try {
            if (user.getEmail().equals(null)) {
            }
        } catch (NullPointerException ex) {
            return "no email found";
        }
        if (user.isActive()) {
            return "Account already active";
        }
        if (!user.isActive()) {
            customerActivateRepo.deleteByUserEmail(email);

            String newToken = UUID.randomUUID().toString();

            CustomerActivate localCustomerActivate = new CustomerActivate();
            localCustomerActivate.setToken(newToken);
            localCustomerActivate.setUserEmail(email);
            localCustomerActivate.setExpiryDate(new Date());

            customerActivateRepo.save(localCustomerActivate);

            sendEmail.sendEmail("RE-ACCOUNT ACTIVATE TOKEN", "http://localhost:8080/customer/activate/"+newToken, email);

            return "Success";
        }

        return "Success";
    }
}
