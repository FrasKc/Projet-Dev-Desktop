package org.appDesktop.repository.activity;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.List;

public interface IActivityRepository {

    String save(Activity activity);

    void delete(String activityId);

    Activity findById(String activityId);

    List<Activity> getAllActivitiesOfWeek(String userId, LocalDate startDateWeek, LocalDate endDateWeek);

    List<Activity> getAllLast28DayActivities(String userId, LocalDate startDay, LocalDate todayDate);

    Activity update(String activityId, Activity updatedActivity);
}
