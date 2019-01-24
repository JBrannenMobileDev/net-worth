package nationalmerchantsassociation.mynetworth.view_layer.application_di;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jbrannen on 11/8/17.
 */

public class NetWorthApplication extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();
        initRealm();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("netWorth_v1.realm").deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
