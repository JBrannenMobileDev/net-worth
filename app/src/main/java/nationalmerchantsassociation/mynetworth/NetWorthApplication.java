package nationalmerchantsassociation.mynetworth;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jbrannen on 11/8/17.
 */

public class NetWorthApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("netWorth_v1.realm").deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
