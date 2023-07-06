package org.appDesktop.controller.user;

import org.appDesktop.model.User;
import org.bson.Document;

public interface IUserController {
    String saveUser(User user) throws Exception;
    User getUser(String userId) throws Exception;

    void updateUser(String userId, User updatedUser) throws Exception;
    boolean verifyDateOfBirth(User user);
}
