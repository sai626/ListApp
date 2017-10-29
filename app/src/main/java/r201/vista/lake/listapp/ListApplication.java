package r201.vista.lake.listapp;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by saii on 28/10/17.
 */

public class ListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
