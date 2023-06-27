package org.appDesktop.controller.activity;

import org.appDesktop.model.Activity;

import java.util.List;

public interface IActivityController {
    public String saveActivity(Activity activity) throws Exception;
    public double calculateLoad(int duration, int rpe);
    public boolean verifActivityValue(Activity activity);
    public List<Activity> getAllActivitiesOfTheWeek();
}