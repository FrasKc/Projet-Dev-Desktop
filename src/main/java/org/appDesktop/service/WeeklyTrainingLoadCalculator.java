package org.appDesktop.service;

import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTrainingLoad;

import java.util.List;

public class WeeklyTrainingLoadCalculator {
    List<Activity> activitiesList;
    List<WeekTrainingLoad> weeklyTrainingLoadList;

    private double averageDailyLoad;

    public WeeklyTrainingLoadCalculator(List<Activity> activitiesList) {
        this.activitiesList = activitiesList;

        averageDailyLoad = sumOfDailyLoads() / activitiesList.size();
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
        return averageDailyLoad / et;
    }

    public int calculateConstraint() {
        double constraint = sumOfWeeklyLoads() * calculateMonotony();
        return Math.round((float)constraint);
    }

    public double calculateFitness() {
        return sumOfDailyLoads() - calculateConstraint();
    }
}
