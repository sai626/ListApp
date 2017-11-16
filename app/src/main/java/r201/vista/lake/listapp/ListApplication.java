package r201.vista.lake.listapp;

import android.app.Application;

import java.io.IOException;

import io.realm.Realm;
import r201.vista.lake.listapp.Utils.Log;

/**
 * Created by saii on 28/10/17.
 */

public class ListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        try {
            Log.getInstance().initializeLogging();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
