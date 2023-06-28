package controller;

import lombok.extern.slf4j.Slf4j;
import org.appDesktop.controller.activity.ActivityControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.appDesktop.service.UserService.getUserId;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the activity controller")
public class ActivityControllerImplTest {
    @Mock
    ActivityRepositoryImpl activityRepository;
    ActivityControllerImpl classUnderTest;
    LocalDate startDateOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    LocalDate endDateOfWeek = startDateOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    Activity activity = new Activity(
            "MMA",
            LocalDate.parse("1995-12-25"),
            330,
            5
    );

    Activity badActivityDurationNegative = new Activity(
            "MMA",
            LocalDate.parse("1995-12-25"),
            -330,
            5
    );

    Activity badActivityRpeNegative = new Activity(
            "MMA",
            LocalDate.parse("1995-12-25"),
            330,
            -5
    );

    Activity badActivityRpeBiggerThanTen = new Activity(
            "MMA",
            LocalDate.parse("1995-12-25"),
            330,
            15
    );

    List<Activity> listActivity = new ArrayList<Activity>();

    String id = "idActivity";

    String userId = getUserId();

    @BeforeEach
    public void setUp() {
        classUnderTest = new ActivityControllerImpl(activityRepository);
    }

    @Test
    public void calculate_load() {
        double load = classUnderTest.calculateLoad(activity.getDuration(), activity.getRpe());
        assertThat(load).isEqualTo(27.50);
    }

    @Test
    public void verif_activity_value() {
        boolean resultDurationNegative = classUnderTest.verifActivityValue(badActivityDurationNegative);
        boolean resultRpeNegative = classUnderTest.verifActivityValue(badActivityRpeNegative);
        boolean resultRpeBiggerThanTen = classUnderTest.verifActivityValue(badActivityRpeBiggerThanTen);

        assertThat(resultDurationNegative).isEqualTo(false);
        assertThat(resultRpeNegative).isEqualTo(false);
        assertThat(resultRpeBiggerThanTen).isEqualTo(false);
    }

    @Test
    public void save_withActivity_shouldCallRepository() throws Exception {
        //Given
        when(activityRepository.save(activity)).thenReturn(id);

        //When
        String result = classUnderTest.saveActivity(activity);

        //Then
        verify(activityRepository).save(activity);
        verify(activityRepository).save(any(Activity.class));
        verify(activityRepository, never()).getAllActivitiesOfWeek(userId, startDateOfWeek, endDateOfWeek);
        verify(activityRepository, times(1)).save(any(Activity.class));
        assertThat(result).isEqualTo(id);
    }

    @Test
    public void get_all_activitie_of_week(){
        when(activityRepository.getAllActivitiesOfWeek(userId, startDateOfWeek, endDateOfWeek)).thenReturn(listActivity);

        List<Activity> result = classUnderTest.getAllActivitiesOfTheWeek();

        verify(activityRepository).getAllActivitiesOfWeek(userId, startDateOfWeek, endDateOfWeek);
        assertThat(result).isEqualTo(listActivity);
    }

    @Test
    public void get_all_28_days_activitie(){
        when(activityRepository.getAllLast28DayActivities(userId, startDateOfWeek, endDateOfWeek)).thenReturn(listActivity);

        List<Activity> result = classUnderTest.getAllLast28DayActivities();

        verify(activityRepository).getAllLast28DayActivities(userId, startDateOfWeek, endDateOfWeek);
        assertThat(result).isEqualTo(listActivity);
    }
}
