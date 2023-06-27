package org.appDesktop.controller.weekTrainingLoad;

import lombok.AllArgsConstructor;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.repository.weekTrainingLoad.IWeekTrainingLoadRepository;

import static org.appDesktop.service.DateService.getFirstDayOfTheWeek;
import static org.appDesktop.service.DateService.getLastDayOfTheWeek;

@AllArgsConstructor
public class WeekTrainingLoadControllerImpl implements IWeekTrainingLoadController{
    IWeekTrainingLoadRepository weekTrainingLoadRepository;
    @Override
    public String saveWeekTrainingLoad(WeekTrainingLoad weekTraningLoad) {
        return weekTrainingLoadRepository.save(weekTraningLoad);
    }


    @Override
    public WeekTrainingLoad getWeekTrainingLoadOfTheCurrentWeek(){
        return weekTrainingLoadRepository.getWeekTraningLoadOfTheWeek(getFirstDayOfTheWeek(), getLastDayOfTheWeek());
    };

    @Override
    public void updateWeekTrainingLoad(WeekTrainingLoad weekTrainingLoad){
        weekTrainingLoadRepository.updateWeekTrainingLoad(weekTrainingLoad);
    }
}
