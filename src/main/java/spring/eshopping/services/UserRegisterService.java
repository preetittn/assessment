package spring.eshopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.eshopping.entities.users.*;
import spring.eshopping.repositories.CustomerActivateRepo;
import spring.eshopping.repositories.SellerRepo;
import spring.eshopping.repositories.UserRepo;
import spring.eshopping.utils.SendEmail;
import spring.eshopping.utils.ValidEmail;
import spring.eshopping.utils.ValidGst;
import spring.eshopping.utils.ValidPassword;

import java.util.*;

@Service
public class UserRegisterService {

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    UserRepo userRepo;

    @Autowired
    CustomerActivateRepo customerActivateRepo;

    @Autowired
    SendEmail sendEmail;

    @Autowired
    ValidGst validGst;

    @Autowired
    SellerRepo sellerRepo;

//REGISTER USER
public String registerCustomer(Customer customer)
{
    //VALID EMAIL
    boolean isValidEmail= ValidEmail.checkEmailValid(customer.getEmail());
    if(!isValidEmail){
        return "email is not valid";
    }
    //UNIQUE EMAIL
    User localCustomer= userRepo.findByEmail(customer.getEmail());
    try{
        if(localCustomer.getEmail().equals((customer.getEmail())))
        {
            return "email already exists";
        }
    }
    catch(NullPointerException ex)
    {
    }
    //VALID PASSWORD
    boolean isValidPassword= ValidPassword.isValidPassword(customer.getPassword());
    if(!isValidPassword)
    {
        return "password is not valid";
    }
    //VALID CONTACT
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    if(customer.getContact().length() != 10)
    {
        return "invalid contact";
    }

    Role role=new Role();
    role.setAuthority("ROLE_CUSTOMER");
    Set<Role> roleSet=new HashSet<>();
    roleSet.add(role);
    customer.setRoles(roleSet);
    customer.setCreatedBy(customer.getFirstName());
    customer.setDateCreated(new Date());
    customer.setLocked(false);
    customer.setPasswordExpired(false);

    userRepo.save(customer);

    String token= UUID.randomUUID().toString();

    CustomerActivate customerActivate=new CustomerActivate();
    customerActivate.setToken(token);
    customerActivate.setUserEmail(customer.getEmail());
    customerActivate.setExpiryDate(new Date());//created time not expiry date

    customerActivateRepo.save(customerActivate);

    sendEmail.sendEmail("Account activate token","http://localhost:8080/customer/activate/"+token,customer.getEmail());

    return "success";
}
//REGISTER SELLER
public String registerSeller(Seller seller)
{
    //VALID GST
    boolean isValidGst = validGst.checkGstValid(seller.getGst());
    if (!isValidGst) {
        return "gst is not valid";
    }
        boolean isValidEmail = ValidEmail.checkEmailValid(seller.getEmail());
        if (!isValidEmail) {
            return "email is not valid";
        }
        //UNIQUE EMAIL
    User localSeller = userRepo.findByEmail(seller.getEmail());
    try {
        if (localSeller.getEmail().equals(seller.getEmail())) {
            return "Email already exists";
        }
    } catch (NullPointerException ex) {
//            ex.printStackTrace();
    }
    //UNIQUE COMPANY NAME
    Seller anotherLocalSeller = sellerRepo.findByCompanyName(seller.getCompanyName());
    try {
        if (anotherLocalSeller.getCompanyName().equalsIgnoreCase(seller.getCompanyName())) {
            return "company name should be unique";
        }
    } catch (NullPointerException ex) {
//            ex.printStackTrace();
    }
    //UNIQUE GST
    List<Seller> anotherLocalSeller1 = sellerRepo.findByGst(seller.getGst());
    boolean flag = false;
    for (Seller seller1 : anotherLocalSeller1) {
        if (seller1.getGst().equals(seller.getGst())) {
            flag = true;
            break;
        }
    }
    try {
        if (flag == true) {
            return "gst should be unique";
        }
    } catch (NullPointerException ex) {
//            ex.printStackTrace();
    }
    //VALID PASSWORD
    boolean isValidPassword = ValidPassword.isValidPassword(seller.getPassword());
    if (!isValidPassword) {
        return "password is invalid";
    }
    //VALID CONTACT
    seller.setPassword(passwordEncoder.encode(seller.getPassword()));
    if (seller.getCompanyContact().length() != 10) {
        return "invalid contact";
    }
    Role role = new Role();
    role.setAuthority("ROLE_SELLER");
    Set<Role> roleSet = new HashSet<>();
    roleSet.add(role);
    seller.setRoles(roleSet);
    seller.setCreatedBy(seller.getFirstName());
    seller.setDateCreated(new Date());
    seller.setLocked(false);
    seller.setPasswordExpired(false);


    Set<Address> addresses = seller.getAddresses();
    addresses.forEach(address -> {
        Address addressSave = address;
        addressSave.setUser(seller);
    });

    userRepo.save(seller);

    return "success";
    }
}
