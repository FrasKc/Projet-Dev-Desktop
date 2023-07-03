package org.appDesktop.repository.user;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.User;
import org.bson.Document;

import static org.appDesktop.mapper.UserMapper.userToDocument;

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
}
