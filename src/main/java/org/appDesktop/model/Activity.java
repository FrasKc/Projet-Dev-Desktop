package org.appDesktop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Activity {
    private String userId;
    private String name;
    private Date date;
    private int duration;
    private int rpe;
    private double load;

    public Activity(String name, Date date, int duration, int rpe) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.rpe = rpe;
    }
}
