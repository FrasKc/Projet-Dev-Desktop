package org.appDesktop.repository.activity;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;

public interface IActivityRepository {

    String save(Activity activity);
}
