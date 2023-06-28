package org.appDesktop.repository.weekTrainingLoad;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.WeekTrainingLoad;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.appDesktop.mapper.WeekTrainingLoadMapper.documentToWeekTrainingLoad;
import static org.appDesktop.mapper.WeekTrainingLoadMapper.weekTrainigLoadToDocument;

@Slf4j
public class WeekTrainingLoadRepositoryImpl implements IWeekTrainingLoadRepository {
    MongoCollection<Document> collection;

    public WeekTrainingLoadRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }

    @Override
    public String save(WeekTrainingLoad weekTraningLoad) {
        InsertOneResult result =  this.collection.insertOne(weekTrainigLoadToDocument(weekTraningLoad));
        return result.getInsertedId().asObjectId().getValue().toString();
    };

    @Override
    public WeekTrainingLoad getWeekTraningLoadOfTheWeek(String userId, LocalDate firstDate, LocalDate lastDate){
        Bson query = Filters.and(
                Filters.gte("date", Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.lte("date", Date.from(lastDate.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                Filters.eq("userId", userId)
        );
        Document document = this.collection.find(query).first();
        if (document != null) {
            return documentToWeekTrainingLoad(document);
        } else {
            return null;
        }
    };

    @Override
    public void updateWeekTrainingLoad(WeekTrainingLoad updatedWeekTrainingLoad) throws MongoWriteException {
        Document document = weekTrainigLoadToDocument(updatedWeekTrainingLoad);
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        try {
            collection.replaceOne(Filters.eq("_id", updatedWeekTrainingLoad.getId()), document, options);
            log.info("Mise à jour réussie.");
        } catch (MongoWriteException e) {
            log.error("Erreur lors de la mise à jour : " + e.getMessage());
        }

    }
}
