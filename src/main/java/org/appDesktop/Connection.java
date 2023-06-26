package org.appDesktop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.SportRepositoryImpl;
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
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            connectionString = prop.getProperty("database.url");
            //get the property value and print it out
            System.out.println(prop.getProperty("database.url"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
