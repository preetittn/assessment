package spring.eshopping.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.eshopping.dtos.SellerAddressDTO;
import spring.eshopping.dtos.SellerProfileDTO;
import spring.eshopping.entities.users.Address;
import spring.eshopping.entities.users.Seller;
import spring.eshopping.repositories.AddressRepo;
import spring.eshopping.repositories.SellerRepo;
import spring.eshopping.utils.SendEmail;
import spring.eshopping.utils.UserEmailFromToken;
import spring.eshopping.utils.ValidGst;
import spring.eshopping.utils.ValidPassword;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SellerProfileService {

    @Autowired
    UserEmailFromToken userEmailFromToken;

    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SendEmail sendEmail;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    ValidGst validGst;

    public SellerProfileDTO viewProfile(HttpServletRequest request) {
        String sellerEmail = userEmailFromToken.getUserEmail(request);
        Seller seller = sellerRepo.findByEmail(sellerEmail);
        SellerProfileDTO sellerProfileDTO = modelMapper.map(seller,SellerProfileDTO.class);
        Set<Address> addresses = seller.getAddresses();
        SellerAddressDTO sellerAddressDTO = modelMapper.map(addresses.stream().findFirst().get(),SellerAddressDTO.class);
        sellerProfileDTO.setAddress(sellerAddressDTO);
        return sellerProfileDTO;
    }

    public String updateSeller(SellerProfileDTO sellerProfileDTO,HttpServletRequest request) {
        if ((sellerProfileDTO.getCompanyContact() != null) && sellerProfileDTO.getCompanyContact().length()!=10) {
            return "invalid contact";
        }
        if ((sellerProfileDTO.getGst() != null) && (validGst.checkGstValid(sellerProfileDTO.getGst())!=true)) {
            return "gst format is invalid";
        }
        if (sellerProfileDTO.getGst()!=null) {
            List<Seller> anotherLocalSeller1 = sellerRepo.findByGst(sellerProfileDTO.getGst());
            boolean flag = false;
            for (Seller seller1 : anotherLocalSeller1) {
                if (seller1.getGst().equals(sellerProfileDTO.getGst())) {
                    flag = true;
                    break;
                }
            }
            try {
                if (flag == true) {
                    return "gst should be unique";
                }
            } catch (NullPointerException ex) {
            }
        }
        Seller seller = sellerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        try {
            if (sellerProfileDTO.getFirstName() != null) {
                seller.setFirstName(sellerProfileDTO.getFirstName());
            }
            if (sellerProfileDTO.getLastName() != null) {
                seller.setLastName(sellerProfileDTO.getLastName());
            }
            if (sellerProfileDTO.getCompanyContact() != null) {
                Seller anotherLocalSeller = sellerRepo.findByCompanyContact(sellerProfileDTO.getCompanyContact());
                try {
                    if (anotherLocalSeller.getCompanyContact().equalsIgnoreCase(seller.getCompanyContact())) {
                        return "company contact number should be unique";
                    }
                } catch (NullPointerException ex) {
//            ex.printStackTrace();
                }
                seller.setCompanyContact(sellerProfileDTO.getCompanyContact());
            }


            if (sellerProfileDTO.getCompanyName() != null) {
                Seller anotherLocalSeller = sellerRepo.findByCompanyName(sellerProfileDTO.getCompanyName());
                try {
                    if (anotherLocalSeller.getCompanyName().equalsIgnoreCase(sellerProfileDTO.getCompanyName())) {
                        return "company name should be unique";
                    }
                } catch (NullPointerException ex) {
//            ex.printStackTrace();
                }
                seller.setCompanyName(sellerProfileDTO.getCompanyName());
            }


            if (sellerProfileDTO.getGst() != null) {
                seller.setGst(sellerProfileDTO.getGst());
            }
        } catch (NullPointerException ex) {}

        sellerRepo.save(seller);
        return "Success";
    }

    public String updatePassword(String pass,String cPass,HttpServletRequest request) {
        if (!pass.contentEquals(cPass)) {
            return "Password and confirm password does not match";
        }
        if (!ValidPassword.isValidPassword(pass)) {
            return "password format invalid";
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Seller seller = sellerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        seller.setPassword(passwordEncoder.encode(pass));

        sellerRepo.save(seller);

        sendEmail.sendEmail("PASSWORD CHANGED","Your password changed",seller.getEmail());

        return "Success";
    }

    public String updateAddress(Long id, SellerAddressDTO addressDTO, HttpServletRequest request) {
        Optional<Address> address = addressRepo.findById(id);
        if (!address.isPresent()) {
            return "no address found with id " + id;
        }
        Seller seller = sellerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        Set<Address> addresses = seller.getAddresses();
        addresses.forEach(a->{
            if (a.getId() == address.get().getId()) {
                a.setAddress(addressDTO.getAddress());
                a.setCity(addressDTO.getCity());
                a.setCountry(addressDTO.getCountry());
                a.setState(addressDTO.getState());
                a.setZipCode(addressDTO.getZipCode());
                a.setAddress(addressDTO.getAddress());
            }
        });
        sellerRepo.save(seller);
        return "Success";
    }
}
