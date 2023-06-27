package org.appDesktop.controller.activity;

import lombok.AllArgsConstructor;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.IActivityRepository;
import org.appDesktop.service.PropertiesReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


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

    @Override
    public List<Activity> getAllActivitiesOfTheWeek() {
        LocalDate startDateOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDateOfWeek = startDateOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return this.activityRepository.getAllActivitiesOfWeek(this.userId, startDateOfWeek, endDateOfWeek);
    }
}