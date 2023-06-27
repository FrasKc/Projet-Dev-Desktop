package org.appDesktop.repository.activity;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface IActivityRepository {

    String save(Activity activity);

    List<Activity> getAllActivitiesOfWeek(String userId, LocalDate startDateWeek, LocalDate endDateWeek);
}
