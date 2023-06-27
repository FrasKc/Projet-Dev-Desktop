package org.appDesktop.controller.weekTrainingLoad;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTraningLoad;
import org.appDesktop.repository.activity.IActivityRepository;
import org.appDesktop.repository.weekTrainingLoad.IWeekTrainingLoadRepository;

public class WeekTrainingLoadControllerImpl implements IWeekTrainingLoadController{
    IWeekTrainingLoadRepository weekTrainingLoadRepository;
    @Override
    public InsertOneResult saveActivity(WeekTraningLoad weekTraningLoad) throws Exception {
        return weekTrainingLoadRepository.save(weekTraningLoad);
    }

}
