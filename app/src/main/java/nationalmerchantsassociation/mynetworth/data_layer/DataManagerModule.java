package nationalmerchantsassociation.mynetworth.data_layer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.database.DbManager;
import nationalmerchantsassociation.mynetworth.data_layer.database.realm.RealmManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DataManagerModule {
    @Singleton
    @Provides
    Realm providesUiRealm(){
        return Realm.getDefaultInstance();
    }

    @Singleton
    @Provides
    RealmManager providesRealmManager(Realm realm){
        return new RealmManager(realm);
    }

    @Singleton
    @Provides
    DbManager providesDbManager(RealmManager realmManager){
        return new DbManager(realmManager);
    }

    @Singleton
    @Provides
    DataManager provideDataManager(DbManager dbManager){
        return new DataManager(dbManager);
    }
}