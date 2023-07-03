package org.appDesktop.controller.user;

import lombok.AllArgsConstructor;
import org.appDesktop.model.User;
import org.appDesktop.repository.user.IUserRepository;
import static org.appDesktop.service.UserService.setUserId;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
public class UserControllerImpl implements IUserController {
    IUserRepository userRepository;
    public String saveUser(User user) throws Exception {
        if(verifyDateOfBirth(user)) {
            System.out.println("Début");
            String userId =  userRepository.save(user);
            System.out.println("2");
            setUserId(userId);
            System.out.println("Set Reussi");
            return userId;
        } else {
            throw new Exception("The date of birth is in the future");
        }
    }

    public boolean verifyDateOfBirth(User user) {
        if(user.getBirthDate().isBefore(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
    }
}
