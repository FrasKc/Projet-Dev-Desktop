package service;

import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.service.WeeklyTrainingLoadCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the weekly training load calculator")
public class WeeklyTrainingLoadCalculatorTest {
    @Mock
    WeeklyTrainingLoadCalculator classUnderTest;

    Activity activityOne = new Activity(
            "userId",
            "MMA",
            Date.from(Instant.now()),
            300,
            8,
            (300/60.0)*8
    );

    Activity activityTwo = new Activity(
            "userId",
            "Rugby",
            Date.from(Instant.now()),
            360,
            6,
            (360/60.0)*6
    );

    Activity activityThree = new Activity(
            "userId",
            "Salle de sport",
            Date.from(Instant.now()),
            360,
            7,
            (360/60.0)*7
    );

    List<Activity> activitiesList = Arrays.asList(activityOne, activityTwo, activityThree);

    @BeforeEach void setUp() {
        classUnderTest = new WeeklyTrainingLoadCalculator(activitiesList);
    }

    @Test
    public void calculate_sumOfDailyLoads() {
        double result = classUnderTest.sumOfDailyLoads();

        assertThat(result).isEqualTo(118);
    }
}
