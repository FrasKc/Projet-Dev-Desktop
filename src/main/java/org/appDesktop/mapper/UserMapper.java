package org.appDesktop.mapper;

import org.appDesktop.model.User;
import org.bson.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public static User documentToUser(Document document) {
        Date date = (Date) document.get("birthDate");
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return new User(
                (String) document.get("firstname"),
                (String) document.get("lastname"),
                localDate,
                (String) document.get("gender")
        );
    }
}
