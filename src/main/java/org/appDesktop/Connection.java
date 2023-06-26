package org.appDesktop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.appDesktop.model.WeekTraningLoad;
import org.appDesktop.repository.weekTrainingLoad.WeekTrainingLoadRepositoryImpl;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.appDesktop.repository.user.UserRepositoryImpl;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.Instant;
import java.util.Properties;

@Slf4j
public class Connection {
    public static void main(String[] args){
        String connectionString = "";

        try (InputStream input = Connection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                log.error("Sorry, unable to find database.properties");
                return;
            }
            prop.load(input);
            connectionString = prop.getProperty("database.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            log.info("Database connection successful");
            MongoDatabase database = mongoClient.getDatabase("AppDesktopDB");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            MongoCollection<Document> userCollection = database.getCollection("user");
            MongoCollection<Document> weeklyTrainingLoadCollection = database.getCollection("weeklyTrainingLoad");

            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
            Activity activity = new Activity(
                    "MMA",
                    Date.from(Instant.now()),
                    300,
                    5,
                    3000
            );
            log.info("Activity saved {}", activityRepository.save(activity));

           UserRepositoryImpl userRepository = new UserRepositoryImpl(userCollection);
           User user = new User(
                   "John",
                   "Doe",
                   Date.from(Instant.parse("1995-12-25T19:00:30.00Z")),
                   "male"
           );
           log.info("User saved {}", userRepository.save(user));

            WeekTrainingLoadRepositoryImpl weekTrainingLoadRepository = new WeekTrainingLoadRepositoryImpl(weeklyTrainingLoadCollection);
            WeekTraningLoad weekTraningLoad = new WeekTraningLoad(
                    20.4,
                    30.8,
                    10,
                    50,
                    190.9
            );
            log.info("WeeklyTrainingLoad saved {}", weekTrainingLoadRepository.save(weekTraningLoad));
        } catch (Exception e) {
            log.error("An error occurred during connection ==> {}", e);
        }
    }
}
