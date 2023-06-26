package org.appDesktop.mapper;


import org.bson.Document;
import org.appDesktop.model.Activity;

public class ActivityMapper {
    public static Document activityToDocument(Activity activity) {
        Document document = new Document()
                .append("name", activity.getName())
                .append("date", activity.getDate())
                .append("duration", activity.getDuration())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad());
        return document;
    }
}
