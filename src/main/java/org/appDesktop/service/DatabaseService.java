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
public class DatabaseService {
    private MongoClient mongoClient;

    public DatabaseService(){
        PropertiesReader propertiesReader = new PropertiesReader("database.properties");
        String connectionString = propertiesReader.getProperty().getProperty("database.url");
        this.mongoClient = MongoClients.create(connectionString);
        log.info("Database connection successful");
    }

    public MongoCollection<Document> getCollection (String collection){
        MongoDatabase database = mongoClient.getDatabase("AppDesktopDB");
        return database.getCollection(collection);
    }

    public UserControllerImpl getUserController(MongoCollection<Document> collection){
        UserRepositoryImpl userRepository = new UserRepositoryImpl(collection);
        return new UserControllerImpl(userRepository);
    };

    public ActivityControllerImpl getActivityController(MongoCollection<Document> collection){
        ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(collection);
        return new ActivityControllerImpl(activityRepository);
    };

    public WeekTrainingLoadControllerImpl getWeekTrainingloadController(MongoCollection<Document> collection){
        WeekTrainingLoadRepositoryImpl weekTrainingLoadRepository = new WeekTrainingLoadRepositoryImpl(collection);
        return new WeekTrainingLoadControllerImpl(weekTrainingLoadRepository);
    };

}
