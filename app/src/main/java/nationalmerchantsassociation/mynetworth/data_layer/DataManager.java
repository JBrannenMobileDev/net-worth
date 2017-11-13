package nationalmerchantsassociation.mynetworth.data_layer;

import nationalmerchantsassociation.mynetworth.data_layer.database.DbManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/8/17.
 */

public class DataManager {
    private static final DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    DbManager dbManager;

    private DataManager() {
        dbManager = new DbManager();
    }

    public void insertOrUpdateAsset(Asset asset){
        dbManager.insertOrUpdateAsset(asset);
    }

    public void insertOrUpdateDebt(Debt debt){
        dbManager.insertOrUpdateDebt(debt);
    }

    public void deleteAsset(String assetName) {
        dbManager.deleteAsset(assetName);
    }

    public void deleteDebt(String debtName) {
        dbManager.deleteDebt(debtName);
    }
}
