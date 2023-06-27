package org.appDesktop.controller.user;

import lombok.AllArgsConstructor;
import org.appDesktop.model.User;
import org.appDesktop.repository.user.IUserRepository;
import org.appDesktop.service.PropertieWriter;

import java.sql.Date;
import java.time.Instant;

@AllArgsConstructor
public class UserControllerImpl implements IUserController {
    IUserRepository userRepository;
    public String saveUser(User user) throws Exception {
        if(verifyDateOfBirth(user)) {
            String userId =  userRepository.save(user);
            PropertieWriter propertieWriter = new PropertieWriter();
            propertieWriter.writeNewPropertie("user.properties", "user.id", userId);
            return userId;
        } else {
            throw new Exception("The date of birth is in the future");
        }
    }

    public boolean verifyDateOfBirth(User user) {
        if(user.getBirthDate().before(Date.from(Instant.now()))) {
            return true;
        } else {
            return false;
        }
    }
}
