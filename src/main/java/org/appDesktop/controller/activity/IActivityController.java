package org.appDesktop.controller.activity;

import org.appDesktop.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface IActivityController {
    String saveActivity(Activity activity) throws Exception;
    double calculateLoad(int duration, int rpe);
    boolean verifActivityValue(Activity activity);
    List<Activity> getAllActivitiesOfTheWeek();
    List<Activity> getAllLast28DayActivities(String userId, LocalDate startDay, LocalDate todayDate);
}