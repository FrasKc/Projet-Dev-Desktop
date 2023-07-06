package org.appDesktop.repository.user;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.appDesktop.model.Activity;
import org.appDesktop.model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static org.appDesktop.mapper.ActivityMapper.activityToDocument;
import static org.appDesktop.mapper.ActivityMapper.documentToActivity;
import static org.appDesktop.mapper.UserMapper.documentToUser;
import static org.appDesktop.mapper.UserMapper.userToDocument;
import static org.appDesktop.service.UserService.getUserId;

public class UserRepositoryImpl implements IUserRepository {
    MongoCollection<Document> collection;

    public UserRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }
    @Override
    public String save(User user) {
        try {
            InsertOneResult result = this.collection.insertOne(userToDocument(user));
            return result.getInsertedId().asObjectId().getValue().toString();
        }catch (Exception e){
            throw e;
        }
    }

    public User get() {
        Bson query = Filters.eq("_id", new ObjectId(getUserId()));
        Document result = collection.find(query).first();

        if (result == null) {
            return null;
        }

        return documentToUser(result);
    }

    public User update(String userId, User updatedUser) {
        Bson filter = Filters.eq("_id", new ObjectId(userId));
        Bson update = new Document("$set", userToDocument(updatedUser));

        UpdateResult result = this.collection.updateOne(filter, update);

        if (result.getModifiedCount() == 0) {
            throw new IllegalArgumentException("Activity not found");
        }

        return updatedUser;
    }

    public User findById(String userId) {
        Bson filter = Filters.eq("_id", new ObjectId(userId));
        Document result = this.collection.find(filter).first();

        if (result == null) {
            return null;
        }

        return documentToUser(result);
    }
}
