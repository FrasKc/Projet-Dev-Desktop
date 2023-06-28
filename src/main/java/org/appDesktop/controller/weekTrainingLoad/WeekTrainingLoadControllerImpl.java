package org.appDesktop.controller.weekTrainingLoad;

import lombok.AllArgsConstructor;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.repository.activity.IActivityRepository;
import org.appDesktop.repository.weekTrainingLoad.IWeekTrainingLoadRepository;
import org.appDesktop.service.PropertiesReader;

import static org.appDesktop.service.DateService.getFirstDayOfTheWeek;
import static org.appDesktop.service.DateService.getLastDayOfTheWeek;
import static org.appDesktop.service.UserService.getUserId;

public class WeekTrainingLoadControllerImpl implements IWeekTrainingLoadController{
    IWeekTrainingLoadRepository weekTrainingLoadRepository;

    String userId;

    public WeekTrainingLoadControllerImpl(IWeekTrainingLoadRepository weekTrainingLoadRepository){
        this.weekTrainingLoadRepository = weekTrainingLoadRepository;
        this.userId = getUserId();
    }

    @Override
    public String saveWeekTrainingLoad(WeekTrainingLoad weekTraningLoad) {
        return weekTrainingLoadRepository.save(weekTraningLoad);
    }

    @Override
    public WeekTrainingLoad getWeekTrainingLoadOfTheCurrentWeek(){
        return weekTrainingLoadRepository.getWeekTraningLoadOfTheWeek(userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
    };

    @Override
    public void updateWeekTrainingLoad(WeekTrainingLoad weekTrainingLoad){
        weekTrainingLoadRepository.updateWeekTrainingLoad(weekTrainingLoad);
    }
}
