package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Connection {
    public static void main(String[] args){
        String connectionString= "mongodb+srv://application-desktop:application-desktop@clusterappdesktop.zxprq4a.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            List<Document> databases =mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> log.info("weelaaaawee", db.toJson()));
        }
    }
}
