package org.appDesktop.repository.weekTrainingLoad;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTraningLoad;

import static org.appDesktop.mapper.ActivityMapper.activityToDocument;

public interface IWeekTrainingLoadRepository {
    InsertOneResult save(WeekTraningLoad weekTraningLoad);
}
