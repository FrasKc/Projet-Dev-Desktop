package org.appDesktop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Activity {
    private String name;
    private Date date;
    private int duration;
    private int rpe;
    private double load;
}
