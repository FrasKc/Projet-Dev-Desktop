import org.appDesktop.controller.ActivityControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the activity controller")
public class ActivityControllerImplTest {
    @Mock
    ActivityRepositoryImpl activityRepository;
    ActivityControllerImpl classUnderTest;
    Activity activity = new Activity(
            "MMA",
            Date.from(Instant.parse("1995-12-25T19:00:30.00Z")),
            330,
            5
    );

    @BeforeEach
    public void setUp() {
        classUnderTest = new ActivityControllerImpl(activityRepository);
    }

    @Test
    public void calculate_load() {
        double load = classUnderTest.calculateLoad(activity.getDuration(), activity.getRpe());
        assertThat(load).isEqualTo(27.50);
    }
}
