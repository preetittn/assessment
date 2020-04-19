package spring.eshopping.bootstrapLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.eshopping.entities.users.Admin;
import spring.eshopping.entities.users.Role;
import spring.eshopping.repositories.UserRepo;

import java.util.*;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepo userRepo;

    @Override
    public void run(ApplicationArguments arguments) throws Exception {
        if (userRepo.count() < 1) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Admin preeti = new Admin();
            preeti.setFirstName("Preeti");
            preeti.setLastName("Upadhyay");
            preeti.setEmail("preeti@mail.com");
            preeti.setCreatedBy("preeti");
            preeti.setDateCreated(new Date());
            preeti.setLastUpdated((new Date()));
            preeti.setUpdatedBy("preeti");
            preeti.setActive(true);
            preeti.setDeleted(false);
            preeti.setPassword(passwordEncoder.encode("password"));

            Role role = new Role();
            role.setAuthority("ROLE_ADMIN");
            Role role1 = new Role();
            role1.setAuthority("ROLE_CUSTOMER");

            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            roleSet.add(role1);

            preeti.setRoles(roleSet);

            userRepo.save(preeti);
            System.out.println("total number of users saved:" + userRepo.count());
        }

    }
}
