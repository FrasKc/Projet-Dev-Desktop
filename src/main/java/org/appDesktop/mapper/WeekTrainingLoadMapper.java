package org.appDesktop.mapper;

import org.appDesktop.model.Activity;
import org.appDesktop.model.WeekTraningLoad;
import org.bson.Document;

public class WeekTrainingLoadMapper {
    public static Document weekTrainigLoadToDocument(WeekTraningLoad weekTraningLoad) {
        Document document = new Document()
                .append("load", weekTraningLoad.getLoad())
                .append("monotony", weekTraningLoad.getMonotony())
                .append("constraint", weekTraningLoad.getConstraint())
                .append("fitness", weekTraningLoad.getFitness())
                .append("acwr", weekTraningLoad.getAcwr());
        return document;
    }
}
