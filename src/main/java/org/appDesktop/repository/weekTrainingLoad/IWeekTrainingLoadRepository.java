package org.appDesktop.repository.weekTrainingLoad;

import org.appDesktop.model.WeekTrainingLoad;

import java.time.LocalDate;

public interface IWeekTrainingLoadRepository {
    String save(WeekTrainingLoad weekTraningLoad);

    WeekTrainingLoad getWeekTraningLoadOfTheWeek(String userId, LocalDate firstDate, LocalDate lastDate);

    void updateWeekTrainingLoad(WeekTrainingLoad updatedWeekTrainingLoad);
}
