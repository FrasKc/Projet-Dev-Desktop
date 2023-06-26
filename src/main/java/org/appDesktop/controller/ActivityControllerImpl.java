package org.appDesktop.controller;

import lombok.AllArgsConstructor;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.IActivityRepository;

@AllArgsConstructor
public class ActivityControllerImpl implements IActivityController {
    IActivityRepository activityRepository;

    @Override
    public String saveActivity(Activity activity) {
        activity.setLoad(calculateLoad(activity.getDuration(), activity.getRpe()));
        return this.activityRepository.save(activity);
    }

    public double calculateLoad(int duration, int rpe) {
        double durationInMinutes = duration/60.0;
        return durationInMinutes * rpe;
    }



}
