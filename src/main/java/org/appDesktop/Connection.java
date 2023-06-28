package org.appDesktop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.controller.activity.ActivityControllerImpl;
import org.appDesktop.controller.user.UserControllerImpl;
import org.appDesktop.controller.weekTrainingLoad.WeekTrainingLoadControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.repository.weekTrainingLoad.WeekTrainingLoadRepositoryImpl;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.appDesktop.repository.user.UserRepositoryImpl;
import org.appDesktop.service.PropertiesReader;
import org.bson.Document;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Slf4j
public class Connection {
    public static void main(String[] args){

        PropertiesReader propertiesReader = new PropertiesReader("database.properties");
        String connectionString = propertiesReader.getProperty().getProperty("database.url");

        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            log.info("Database connection successful");
            MongoDatabase database = mongoClient.getDatabase("AppDesktopDB");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            MongoCollection<Document> userCollection = database.getCollection("user");
            MongoCollection<Document> weeklyTrainingLoadCollection = database.getCollection("weeklyTrainingLoad");

            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
            ActivityControllerImpl activityController = new ActivityControllerImpl(activityRepository);
            Activity activity = new Activity(
                    "649988ddefe3540f408a41c1",
                    "Foot",
                    LocalDate.now(),
                    300,
                    5
            );


            String id = activityController.saveActivity(activity);
            log.info("Activity saved {}", id);
            UserRepositoryImpl userRepository = new UserRepositoryImpl(userCollection);
           User user = new User(
                   "John",
                   "Doe",
                   Date.from(Instant.parse("1995-12-25T19:00:30.00Z")),
                   "male"
           );
            UserControllerImpl userController = new UserControllerImpl(userRepository);
            log.info("User saved {}", userController.saveUser(user));
            WeekTrainingLoadRepositoryImpl weekTrainingLoadRepository = new WeekTrainingLoadRepositoryImpl(weeklyTrainingLoadCollection);
            WeekTrainingLoadControllerImpl weekTrainingLoadController = new WeekTrainingLoadControllerImpl(weekTrainingLoadRepository);
            WeekTrainingLoad weekTraningLoad = new WeekTrainingLoad(
                    1222,
                    20,
                    12,
                    12,
                    2,
                    Date.from(Instant.now())

            );
            log.info("WeeklyTrainingLoad saved {}", weekTrainingLoadController.saveWeekTrainingLoad(weekTraningLoad));
        } catch (Exception e) {
            log.error("An error occurred during connection ==> {}", e);
        }
    }
}
