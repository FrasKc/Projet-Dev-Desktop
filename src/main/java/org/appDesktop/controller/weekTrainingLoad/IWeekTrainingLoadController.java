package org.appDesktop.controller.weekTrainingLoad;

import org.appDesktop.model.WeekTrainingLoad;

import java.time.LocalDate;

public interface IWeekTrainingLoadController {

    String saveWeekTrainingLoad(WeekTrainingLoad weekTraningLoad);

    WeekTrainingLoad getWeekTrainingLoadOfTheCurrentWeek();

    void updateWeekTrainingLoad(WeekTrainingLoad weekTrainingLoad);
}
