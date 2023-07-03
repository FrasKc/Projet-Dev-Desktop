package org.appDesktop.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.controller.activity.ActivityControllerImpl;
import org.appDesktop.controller.user.UserControllerImpl;
import org.appDesktop.controller.weekTrainingLoad.WeekTrainingLoadControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.appDesktop.model.WeekTrainingLoad;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.appDesktop.repository.user.UserRepositoryImpl;
import org.appDesktop.repository.weekTrainingLoad.WeekTrainingLoadRepositoryImpl;
import org.bson.Document;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Slf4j
@Getter
public class DatabaseService {
    private MongoDatabase database;
    private MongoCollection<Document> activityCollection;
    private MongoCollection<Document> userCollection;
    private MongoCollection<Document> weeklyTrainingLoadCollection;
    private ActivityRepositoryImpl activityRepository;
    private ActivityControllerImpl activityController;
    private WeekTrainingLoadRepositoryImpl weekTrainingLoadRepository;
    private WeekTrainingLoadControllerImpl weekTrainingLoadController;
    private UserRepositoryImpl userRepository;
    private UserControllerImpl userController;

    public DatabaseService(){
        PropertiesReader propertiesReader = new PropertiesReader("database.properties");
        String connectionString = propertiesReader.getProperty().getProperty("database.url");
        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            log.info("Database connection successful");
            this.database = mongoClient.getDatabase("AppDesktopDB");
            this.activityCollection = database.getCollection("activity");
            this.userCollection = database.getCollection("user");
            this.weeklyTrainingLoadCollection = database.getCollection("weeklyTrainingLoad");
            this.activityRepository = new ActivityRepositoryImpl(this.activityCollection);
            this.activityController = new ActivityControllerImpl(this.activityRepository);

            this.weekTrainingLoadRepository = new WeekTrainingLoadRepositoryImpl(this.weeklyTrainingLoadCollection);
            this.weekTrainingLoadController = new WeekTrainingLoadControllerImpl(this.weekTrainingLoadRepository);

            this.userRepository = new UserRepositoryImpl(this.userCollection);
            this.userController = new UserControllerImpl(this.userRepository);
        } catch (Exception e) {
            log.error("An error occurred during connection ==> {}", e);
        }
    }
}
