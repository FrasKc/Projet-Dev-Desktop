package controller;

import lombok.extern.slf4j.Slf4j;
import org.appDesktop.controller.user.IUserController;
import org.appDesktop.controller.user.UserControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.appDesktop.repository.user.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the user controller")
public class UserControllerImplTest {

    @Mock
    IUserRepository userRepository;
    IUserController classUnderTest;

    User user = new User(
            "John",
            "Doe",
            Date.from(Instant.parse("1995-12-25T19:00:30.00Z")),
            "male"
    );
    String userId = "userId";

    Date futurDateOfBirth = new Date(Date.from(Instant.now()).getTime() + 86400);
    User badUserWithFuturDateOfBirth = new User(
            "John",
            "Doe",
            futurDateOfBirth,
            "male"
    );

    @BeforeEach
    public void setUp() { classUnderTest = new UserControllerImpl(userRepository); }

    @Test
    @DisplayName("Test if the save method of the repository is called with a User")
    public void save_withUser_shouldCallRepository() throws Exception {
        // Arrange
        when(userRepository.save(user)).thenReturn(userId);

        // Act
        String result = classUnderTest.saveUser(user);

        // Assert
        verify(userRepository).save(user);
        verify(userRepository).save(any(User.class));
        verify(userRepository, times(1)).save(any(User.class));
        assertThat(result).isEqualTo(userId);
    }

    @Test
    @DisplayName("Test if verify method detect a past date")
    public void verify_dateOfBirthOfUser_isInPast() {
        boolean result = classUnderTest.verifyDateOfBirth(user);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test if verify method detect a future date")
    public void verify_dateOfBirthOfUser_inIsFuture() {
        boolean result = classUnderTest.verifyDateOfBirth(badUserWithFuturDateOfBirth);

        assertThat(result).isFalse();
    }
}
