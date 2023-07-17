package org.appDesktop.controller.activity;

import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.IActivityRepository;

import java.time.LocalDate;
import java.util.List;
import static org.appDesktop.service.UserService.getUserId;
import static org.appDesktop.service.DateService.*;


public class ActivityControllerImpl implements IActivityController {
    IActivityRepository activityRepository;
    String userId;

    public ActivityControllerImpl(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        this.userId = getUserId();
    }

    @Override
    public String saveActivity(Activity activity) throws Exception {
        if (verifActivityValue(activity)) {
            activity.setLoad(calculateLoad(activity.getDuration(), activity.getRpe()));
            return this.activityRepository.save(activity);
        } else {
            throw new Exception("Bad activity");
        }
    }

    @Override
    public void deleteActivity(String id) throws Exception {
        if (id != null) {
            this.activityRepository.delete(id);
        } else {
            throw new Exception("Activity not found");
        }
    }

    @Override
    public void updateActivity(String activityId, Activity updatedActivity) throws Exception {
        Activity existingActivity = this.activityRepository.findById(activityId);

        if (existingActivity == null) {
            throw new Exception("Activity not found");
        }

        // Perform any necessary updates on the existing activity
        existingActivity.setName(updatedActivity.getName());
        existingActivity.setDuration(updatedActivity.getDuration());
        existingActivity.setRpe(updatedActivity.getRpe());

        // Save the updated activity
        this.activityRepository.update(activityId, existingActivity);
    }


    @Override
    public double calculateLoad(int duration, int rpe) {
        double durationInMinutes = duration/60.0;
        return durationInMinutes * rpe;
    }

    @Override
    public boolean verifActivityValue(Activity activity) {
        if (activity.getDuration() < 0 || activity.getRpe() < 0 || activity.getRpe() > 10){
            return false;
        }
        return true;
    }

    @Override
    public List<Activity> getAllActivities() {
        return this.activityRepository.getAllActivities(this.userId);
    }

    @Override
    public List<Activity> getAllActivitiesOfTheWeek() {
        return this.activityRepository.getAllActivitiesOfWeek(this.userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
    }

    @Override
    public List<Activity> getAllLast28DayActivities(){
        return this.activityRepository.getAllLast28DayActivities(this.userId, getLast28Day(), getTodayDate());
    }

    @Override
    public Activity findActivityById(String activityId) {
        return this.activityRepository.findById(activityId);
    }
}