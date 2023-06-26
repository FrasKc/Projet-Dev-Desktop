package org.appDesktop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeekTraningLoad {
    private double load;
    private double monotony;
    private int constraint;
    private int fitness;
    private double acwr;

}
