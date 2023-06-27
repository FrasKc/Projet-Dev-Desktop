package org.appDesktop.controller.weekTrainingLoad;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTraningLoad;

public interface IWeekTrainingLoadController {
    public InsertOneResult saveActivity(WeekTraningLoad weekTraningLoad) throws Exception;
}
