package org.appDesktop.controller;

import lombok.AllArgsConstructor;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.IActivityRepository;

@AllArgsConstructor
public class ActivityControllerImpl implements IActivityController {
    IActivityRepository activityRepository;

    @Override
    public String saveActivity(Activity activity) throws Exception {
        if (verifActivityValue(activity)){
            activity.setLoad(calculateLoad(activity.getDuration(), activity.getRpe()));
            return this.activityRepository.save(activity);
        }else{
            throw new Exception("Bad activity");
        }
    }

    public double calculateLoad(int duration, int rpe) {
        double durationInMinutes = duration/60.0;
        return durationInMinutes * rpe;
    }

    public boolean verifActivityValue(Activity activity) {
        if (activity.getDuration() < 0 || activity.getRpe() < 0 || activity.getRpe() > 10){
            return false;
        }
        return true;
    }
}
