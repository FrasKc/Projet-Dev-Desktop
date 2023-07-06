package org.appDesktop.controller.user;

import lombok.AllArgsConstructor;
import org.appDesktop.model.User;
import org.appDesktop.repository.user.IUserRepository;

import static org.appDesktop.service.UserService.setUserId;

import java.time.LocalDate;

@AllArgsConstructor
public class UserControllerImpl implements IUserController {
    IUserRepository userRepository;
    public String saveUser(User user) throws Exception {
        if(verifyDateOfBirth(user)) {
            String userId =  userRepository.save(user);
            setUserId(userId);
            return userId;
        } else {
            throw new Exception("The date of birth is in the future");
        }
    }

    @Override
    public User getUser(String userId) throws Exception {
        User user = userRepository.findById(userId);

        if(user == null) {
            throw new Exception("User doesn't exist");
        }

        return user;
    }

    public void updateUser(String userId, User updatedUser) {
        this.userRepository.update(userId, updatedUser);
    }

    public boolean verifyDateOfBirth(User user) {
        if(user.getBirthDate().isBefore(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
    }
}
