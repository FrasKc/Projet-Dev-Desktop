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

    WeekTrainingLoad weeklyTrainingLoadOne = new WeekTrainingLoad(
            1500,
            20,
            12,
            12,
            2,
            Date.from(Instant.now())
    );
    WeekTrainingLoad weeklyTrainingLoadTwo = new WeekTrainingLoad(
            1200,
            20,
            12,
            12,
            2,
            Date.from(Instant.now())
    );
    WeekTrainingLoad weeklyTrainingLoadThree = new WeekTrainingLoad(
            2000,
            20,
            12,
            12,
            2,
            Date.from(Instant.now())
    );
    List<WeekTrainingLoad> weeklyTrainingLoadList = Arrays.asList(weeklyTrainingLoadOne, weeklyTrainingLoadTwo, weeklyTrainingLoadThree);

    @BeforeEach void setUp() {
        classUnderTest = new WeeklyTrainingLoadCalculator(activitiesList, weeklyTrainingLoadList);
    }

    @Test
    @DisplayName("Test the calcul of the sum of daily loads")
    public void calculate_sumOfDailyLoads() {
        double result = classUnderTest.sumOfDailyLoads();

        assertThat(result).isEqualTo(118);
    }

    @Test
    @DisplayName("Test the calcul of the sum of weekly loads")
    public void calculate_sumOfWeeklyLoads() {
        double result = classUnderTest.sumOfWeeklyLoads();

        assertThat(result).isEqualTo(4700);
    }

    @Test
    @DisplayName("Test the calcul of the smonotony")
    public void calculate_monotony() {
        double result = classUnderTest.calculateMonotony();

        assertThat(result).isEqualTo(10.06);
    }

    @Test
    @DisplayName("Test the calcul of the constraint")
    public void calculate_constraint() {
        double result = classUnderTest.calculateConstraint();

        assertThat(result).isEqualTo(1187);
    }

    @Test
    @DisplayName("Test the calcul of the fitness")
    public void calculate_fitness() {
        double result = classUnderTest.calculateFitness();

        assertThat(result).isEqualTo(-1069);
    }

    @Test
    @DisplayName("Test the calcul of the acwr")
    public  void calculate_acwr() {
        double result = classUnderTest.calculateAcwr();

        assertThat(result).isEqualTo(0.03);
    }
}
