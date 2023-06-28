package org.appDesktop.controller.activity;

import lombok.AllArgsConstructor;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.IActivityRepository;
import org.appDesktop.service.DateService;
import org.appDesktop.service.PropertiesReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.appDesktop.service.DateService.*;


public class ActivityControllerImpl implements IActivityController {
    IActivityRepository activityRepository;
    String userId;

    public ActivityControllerImpl(IActivityRepository activityRepository){
        this.activityRepository = activityRepository;
        PropertiesReader propertiesReader = new PropertiesReader("user.properties");
        this.userId = propertiesReader.getProperty().getProperty("user.id");
    }
    @Override
    public String saveActivity(Activity activity) throws Exception {
        if (verifActivityValue(activity)){
            activity.setLoad(calculateLoad(activity.getDuration(), activity.getRpe()));
            return this.activityRepository.save(activity);
        }else{
            throw new Exception("Bad activity");
        }
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
    public List<Activity> getAllActivitiesOfTheWeek() {
        return this.activityRepository.getAllActivitiesOfWeek(this.userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
    }

    @Override
    public List<Activity> getAllLast28DayActivities(String userId, LocalDate startDay, LocalDate todayDate){
        return this.activityRepository.getAllLast28DayActivities(this.userId, getLast28Day(), getTodayDate());
    }
}