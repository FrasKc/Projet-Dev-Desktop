package controller;

import lombok.extern.slf4j.Slf4j;
import org.appDesktop.controller.weekTrainingLoad.IWeekTrainingLoadController;
import org.appDesktop.controller.weekTrainingLoad.WeekTrainingLoadControllerImpl;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.repository.weekTrainingLoad.IWeekTrainingLoadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Date;
import java.time.Instant;


import static org.appDesktop.service.DateService.getFirstDayOfTheWeek;
import static org.appDesktop.service.DateService.getLastDayOfTheWeek;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.appDesktop.service.UserService.getUserId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the activity controller")
public class WeekTrainingLoadControllerImplTest {
    @Mock
    IWeekTrainingLoadRepository weekTrainingLoadRepository;
    IWeekTrainingLoadController classUnderTest;
    WeekTrainingLoad weekTrainingLoad = new WeekTrainingLoad(
            1222,
            20,
            12,
            12,
            2,
            Date.from(Instant.now())
    );

    WeekTrainingLoad weekTrainingLoadFalse = new WeekTrainingLoad(
            1222,
            20,
            12,
            12,
            -2,
            Date.from(Instant.now())
    );

    String id = "idWeekTrainingLoad";

    String userId = getUserId();

    @BeforeEach
    public void setUp() {
        classUnderTest = new WeekTrainingLoadControllerImpl(weekTrainingLoadRepository);
    }

    @Test
    public void save_withWeekTrainingLoad_shouldCallRepository() {
        //Given
        when(weekTrainingLoadRepository.save(weekTrainingLoad)).thenReturn(id);

        //When
        String result = classUnderTest.saveWeekTrainingLoad(weekTrainingLoad);

        //Then
        verify(weekTrainingLoadRepository).save(weekTrainingLoad);
        verify(weekTrainingLoadRepository).save(any(WeekTrainingLoad.class));
        verify(weekTrainingLoadRepository, times(1)).save(any(WeekTrainingLoad.class));
        assertThat(result).isEqualTo(id);
    }

    @Test
    public void get_WeekTrainingLoad_of_the_week_shouldCallRepository() {
        //Given
        when(weekTrainingLoadRepository.getWeekTraningLoadOfTheWeek(userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek())).thenReturn(weekTrainingLoad);

        //When
        WeekTrainingLoad result = classUnderTest.getWeekTrainingLoadOfTheCurrentWeek();

        //Then
        verify(weekTrainingLoadRepository).getWeekTraningLoadOfTheWeek(userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
        verify(weekTrainingLoadRepository).getWeekTraningLoadOfTheWeek(userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
        verify(weekTrainingLoadRepository, times(1)).getWeekTraningLoadOfTheWeek(userId, getFirstDayOfTheWeek(), getLastDayOfTheWeek());
        assertThat(result).isEqualTo(weekTrainingLoad);
    }

    @Test
    public void update_WeekTrainingLoad_of_the_week_shouldCallRepository() {
        classUnderTest.updateWeekTrainingLoad(weekTrainingLoad);
        //Then
        verify(weekTrainingLoadRepository).updateWeekTrainingLoad(weekTrainingLoad);
        verify(weekTrainingLoadRepository).updateWeekTrainingLoad(weekTrainingLoad);
        verify(weekTrainingLoadRepository, times(1)).updateWeekTrainingLoad(weekTrainingLoad);
    }

}
