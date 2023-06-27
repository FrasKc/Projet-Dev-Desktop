package org.appDesktop.mapper;


import org.bson.Document;
import org.appDesktop.model.Activity;

import java.util.Date;

public class ActivityMapper {
    public static Document activityToDocument(Activity activity) {
        Document document = new Document()
                .append("userId", activity.getUserId())
                .append("name", activity.getName())
                .append("date", activity.getDate())
                .append("duration", activity.getDuration())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad());
        return document;
    }
    public static Activity documentToActivity(Document document) {
        Activity Activity = new Activity(
                (String) document.get("userId"),
                (String) document.get("name"),
                (Date) document.get("date"),
                (int) document.get("duration"),
                (int) document.get("rpe"),
                (double) document.get("load")
        );
        return Activity;
    }
}
