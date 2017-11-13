package nationalmerchantsassociation.mynetworth.data_layer.database;

import nationalmerchantsassociation.mynetworth.data_layer.database.realm.RealmManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 8/3/17.
 */

public class DbManager {
    private RealmManager realmManager;

    public DbManager() {
        realmManager = new RealmManager();
    }

    public void insertOrUpdateAsset(Asset asset){
        realmManager.insertOrUpdateAsset(asset);
    }

    public void insertOrUpdateDebt(Debt debt){
        realmManager.insertOrUpdateDebt(debt);
    }

    public void nukeDb() {
        realmManager.nukeDb();
    }

    public void deleteAsset(String assetName) {
        realmManager.deleteAsset(assetName);
    }

    public void deleteDebt(String debtName){
        realmManager.deleteDebt(debtName);
    }
}
