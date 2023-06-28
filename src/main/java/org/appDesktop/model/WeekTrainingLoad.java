package org.appDesktop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class WeekTrainingLoad {
    private String id;
    private double load;
    private double monotony;
    private int constraint;
    private int fitness;
    private double acwr;
    private Date creationDate;

    public WeekTrainingLoad(double load, double monotony, int constraint, int fistness, double acwr, Date creationDate){
        this.load = load;
        this.monotony = monotony;
        this.constraint = constraint;
        this.fitness = fistness;
        this.acwr = acwr;
        this.creationDate = creationDate;
    }
}
