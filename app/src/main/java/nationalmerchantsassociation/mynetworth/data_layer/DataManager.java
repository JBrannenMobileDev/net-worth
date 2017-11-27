package nationalmerchantsassociation.mynetworth.data_layer;

import javax.inject.Inject;

import nationalmerchantsassociation.mynetworth.data_layer.database.DbManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/8/17.
 */

public class DataManager {
    private DbManager dbManager;

    @Inject
    public DataManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void updateAsset(ValueItem newValue, String assetName) {
        dbManager.updateAsset(newValue, assetName);
    }

    public void updateAsset(String assetName, String assetCategory, String previousName) {
        dbManager.updateAsset(assetName, assetCategory, previousName);
    }

    public void updateDebt(ValueItem newValue, String debtName) {
        dbManager.updateDebt(newValue, debtName);
    }

    public void updateDebt(String debtName, String categoryName, String previousName) {
        dbManager.updateDebt(debtName, categoryName, previousName);
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
