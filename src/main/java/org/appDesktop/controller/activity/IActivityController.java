package org.appDesktop.controller.activity;

import org.appDesktop.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface IActivityController {
    String saveActivity(Activity activity) throws Exception;

    void deleteActivity(String id) throws Exception;

    Activity findActivityById(String activityId);

    void updateActivity(String activityId, Activity activityUpdated) throws Exception;

    double calculateLoad(int duration, int rpe);
    boolean verifActivityValue(Activity activity);
    List<Activity> getAllActivitiesOfTheWeek();
    List<Activity> getAllLast28DayActivities();
}