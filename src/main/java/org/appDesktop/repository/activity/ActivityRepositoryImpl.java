package org.appDesktop.repository.activity;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.appDesktop.model.Activity;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.appDesktop.mapper.ActivityMapper.activityToDocument;
import static org.appDesktop.mapper.ActivityMapper.documentToActivity;

public class ActivityRepositoryImpl implements IActivityRepository {
    MongoCollection<Document> collection;

    public ActivityRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }
    @Override
    public String save(Activity activity) {
        InsertOneResult result = this.collection.insertOne(activityToDocument(activity));
        return result.getInsertedId().asObjectId().getValue().toString();
    }

    @Override
    public void delete(String activityId) {
        if (activityId == null) {
            throw new IllegalArgumentException("Invalid activityId");
        }

        Bson filter = Filters.eq("_id", new ObjectId(activityId));
        DeleteResult result = this.collection.deleteOne(filter);

        if (result.getDeletedCount() == 0) {
            throw new IllegalArgumentException("Activity not found");
        }
    }


    @Override
    public Activity update(String activityId, Activity updatedActivity) {
        Bson filter = Filters.eq("_id", new ObjectId(activityId));
        Bson update = new Document("$set", activityToDocument(updatedActivity));

        UpdateResult result = this.collection.updateOne(filter, update);

        if (result.getModifiedCount() == 0) {
            throw new IllegalArgumentException("Activity not found");
        }

        return updatedActivity;
    }

    @Override
    public Activity findById(String activityId) {
        Bson filter = Filters.eq("_id", new ObjectId(activityId));
        Document result = this.collection.find(filter).first();

        if (result == null) {
            return null;
        }

        return documentToActivity(result);
    }

    @Override
    public List<Activity> getAllActivitiesOfWeek(String userId, LocalDate startDateWeek, LocalDate endDateWeek) {
        Bson query = Filters.and(
                Filters.gte("date", Date.from(startDateWeek.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.lte("date", Date.from(endDateWeek.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.eq("userId", userId)
        );
        List<Activity> activities = new ArrayList<Activity>();
        for (Document document : this.collection.find()) {
            activities.add(documentToActivity(document));
        }
        return activities;
    }

    @Override
    public List<Activity> getAllLast28DayActivities(String userId, LocalDate startDay, LocalDate todayDate) {
        Bson query = Filters.and(
                Filters.gte("date", Date.from(startDay.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.lte("date", Date.from(todayDate.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.eq("userId", userId)
        );
        List<Activity> activities = new ArrayList<Activity>();
        for (Document document : this.collection.find()) {
            activities.add(documentToActivity(document));
        }
        return activities;
    }

}
