package org.appDesktop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.SportRepositoryImpl;
import org.bson.Document;

import java.sql.Date;
import java.time.Instant;

@Slf4j
public class Connection {
    public static void main(String[] args){
        String connectionString= "mongodb+srv://application-desktop:application-desktop@clusterappdesktop.zxprq4a.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            log.info("Database connection successful");
            MongoDatabase database = mongoClient.getDatabase("AppDesktopDB");
            MongoCollection<Document> activityCollection = database.getCollection("activty");
            SportRepositoryImpl activityRepository = new SportRepositoryImpl(activityCollection);
            Activity activity = new Activity(
                    "MMA", Date.from(Instant.now()),
                    300,
                    5,
                    3000
            );
            log.info("Book saved {}", activityRepository.save(activity));
        } catch (Exception e) {
            log.error("An error occurred during connection ==> {}", e);
        }
    }
}
