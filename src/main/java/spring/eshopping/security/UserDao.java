package spring.eshopping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.eshopping.entities.users.User;
import spring.eshopping.repositories.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserRepo userRepo;

    AppUser loadUserByUserEmail(String email) {
        User user = userRepo.findByEmail(email);
        System.out.println(user);
        if (email != null) {
            List<GrantAuthorityImpl> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                GrantAuthorityImpl grantAuthority = new GrantAuthorityImpl(role.getAuthority());
                authorities.add(grantAuthority);
            });
            return new AppUser(user.getFirstName(), user.getPassword(), authorities,user.isActive(),!user.isLocked(),!user.isPasswordExpired());
        } else {
            throw new RuntimeException();
        }

    }
}
