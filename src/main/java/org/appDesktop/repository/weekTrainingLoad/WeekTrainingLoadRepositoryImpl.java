package org.appDesktop.repository.weekTrainingLoad;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.WeekTraningLoad;
import org.bson.Document;

import static org.appDesktop.mapper.WeekTrainingLoadMapper.weekTrainigLoadToDocument;

public class WeekTrainingLoadRepositoryImpl implements IWeekTrainingLoadRepository {
    MongoCollection<Document> collection;

    public WeekTrainingLoadRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }
    @Override
    public InsertOneResult save(WeekTraningLoad weekTraningLoad) {
        return this.collection.insertOne(weekTrainigLoadToDocument(weekTraningLoad));
    }
}
