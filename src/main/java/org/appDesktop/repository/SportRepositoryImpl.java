package org.appDesktop.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.appDesktop.model.Activity;



import static org.appDesktop.mapper.ActivityMapper.activityToDocument;

public class SportRepositoryImpl implements  ISportRepository {
    MongoCollection<Document> collection;

    public SportRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }
    @Override
    public InsertOneResult save(Activity activity) {
        return this.collection.insertOne(activityToDocument(activity));
    }
}
