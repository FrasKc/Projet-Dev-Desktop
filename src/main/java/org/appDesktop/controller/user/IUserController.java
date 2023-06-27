package org.appDesktop.controller.user;

import org.appDesktop.model.User;

public interface IUserController {
    String saveUser(User user) throws Exception;
    boolean verifyDateOfBirth(User user);
}
