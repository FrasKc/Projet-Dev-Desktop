package org.appDesktop.service;

import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTrainingLoad;

import java.util.List;

public class WeeklyTrainingLoadCalculator {
    List<Activity> activitiesList;
    List<WeekTrainingLoad> weeklyTrainingLoadList;

    private double averageDailyLoad;

    public WeeklyTrainingLoadCalculator(List<Activity> activitiesList, List<WeekTrainingLoad> weeklyTrainingLoadList) {
        this.activitiesList = activitiesList;
        this.weeklyTrainingLoadList = weeklyTrainingLoadList;

        averageDailyLoad = sumOfDailyLoads() / 7;
    }

    public double sumOfDailyLoads() {
        double result = 0;
        for(Activity activity : activitiesList) {
            result += activity.getLoad();
        }
        return result;
    }

    public double sumOfWeeklyLoads() {
        double result = 0;
        for(WeekTrainingLoad weeklyTrainingLoad : weeklyTrainingLoadList) {
            result += weeklyTrainingLoad.getLoad();
        }
        return result;
    }

    public double calculateMonotony() {
        int days = 7 - 1;
        double et = Math.sqrt(averageDailyLoad / days);
        return (double)Math.round((averageDailyLoad / et)*100) / 100;
    }

    public int calculateConstraint() {
        double constraint = sumOfDailyLoads() * calculateMonotony();
        return Math.round((float)constraint);
    }

    public double calculateFitness() {
        return sumOfDailyLoads() - calculateConstraint();
    }

    public double calculateAcwr() {
        double acuteLoad = sumOfDailyLoads();
        double chronicLoad = sumOfWeeklyLoads();
        return (double)Math.round((acuteLoad / chronicLoad)*100) / 100;
    }
}
