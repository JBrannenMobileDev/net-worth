package nationalmerchantsassociation.mynetworth.data_layer;

import javax.inject.Inject;

import nationalmerchantsassociation.mynetworth.data_layer.database.DbManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/8/17.
 */

public class DataManager {
    private DbManager dbManager;

    @Inject
    public DataManager(DbManager dbManager) {
        this.dbManager = dbManager;
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
