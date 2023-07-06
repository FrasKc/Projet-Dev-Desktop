package org.appDesktop.mapper;


import org.bson.Document;
import org.appDesktop.model.Activity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ActivityMapper {
    public static Document activityToDocument(Activity activity) {
        return new Document()
                .append("userId", activity.getUserId())
                .append("name", activity.getName())
                .append("date", activity.getDate())
                .append("duration", activity.getDuration())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad());
    }
    public static Activity documentToActivity(Document document) {
        Date date = (Date) document.get("date");
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return new Activity(
                (String) document.get("_id").toString(),
                (String) document.get("userId"),
                (String) document.get("name"),
                localDate,
                (int) document.get("duration"),
                (int) document.get("rpe"),
                (double) document.get("load")
        );
    }
}
