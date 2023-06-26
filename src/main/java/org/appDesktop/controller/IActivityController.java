package org.appDesktop.controller;

import org.appDesktop.model.Activity;

public interface IActivityController {
    public String saveActivity(Activity activity) throws Exception;
    public double calculateLoad(int duration, int rpe);
    public boolean verifActivityValue(Activity activity);
}
