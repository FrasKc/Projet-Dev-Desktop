package org.appDesktop.mapper;

import org.appDesktop.model.WeekTrainingLoad;
import org.bson.Document;

import java.util.Date;

public class WeekTrainingLoadMapper {
    public static Document weekTrainigLoadToDocument(WeekTrainingLoad weekTraningLoad) {
        Document document = new Document()
                .append("load", weekTraningLoad.getLoad())
                .append("monotony", weekTraningLoad.getMonotony())
                .append("constraint", weekTraningLoad.getConstraint())
                .append("fitness", weekTraningLoad.getFitness())
                .append("acwr", weekTraningLoad.getAcwr())
                .append("creationDate", weekTraningLoad.getCreationDate());
        return document;
    }

    public  static WeekTrainingLoad documentToWeekTrainingLoad(Document document) {
        return new WeekTrainingLoad(
                (String) document.get("_id"),
                (double) document.get("load"),
                (double) document.get("monotony"),
                (int) document.get("constraint"),
                (int) document.get("fitness"),
                (double) document.get("acwr"),
                (Date) document.get("creationDate")
        );
    }
}
