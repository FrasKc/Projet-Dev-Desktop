package org.appDesktop.mapper;


import org.bson.Document;
import org.appDesktop.model.Activity;

import java.time.LocalDate;

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
        return new Activity(
                (String) document.get("userId"),
                (String) document.get("name"),
                (LocalDate) document.get("date"),
                (int) document.get("duration"),
                (int) document.get("rpe"),
                (double) document.get("load")
        );
    }
}
