package org.appDesktop.repository.user;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.User;

public interface IUserRepository {
    String save(User user);
}
