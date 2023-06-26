package org.appDesktop.repository;

import com.mongodb.client.result.InsertOneResult;
import org.appDesktop.model.Activity;

public interface ISportRepository {

    InsertOneResult save(Activity activity);
}
