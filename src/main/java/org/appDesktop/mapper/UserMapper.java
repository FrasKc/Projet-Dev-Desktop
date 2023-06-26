package org.appDesktop.mapper;

import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.bson.Document;

import java.util.Date;

public class UserMapper {
    public static Document userToDocument(User user) {
        Document document = new Document()
                .append("firstname", user.getFirstname())
                .append("lastname", user.getLastname())
                .append("birthDate", user.getBirthDate())
                .append("gender", user.getGender());

        return document;
    }
}
