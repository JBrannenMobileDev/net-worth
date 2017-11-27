package nationalmerchantsassociation.mynetworth.data_layer.database;

import javax.inject.Inject;

import nationalmerchantsassociation.mynetworth.data_layer.database.realm.RealmManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 8/3/17.
 */

public class DbManager {
    private RealmManager realmManager;

    @Inject
    public DbManager(RealmManager realmManager) {
        this.realmManager = realmManager;
    }

    public void insertOrUpdateAssetValueItem(ValueItem itemToSave, String assetName){
        Asset asset = realmManager.getAsset(assetName);
        boolean alreadyExists = asset.getAssetValues().stream().anyMatch(item -> item.getDate().equals(itemToSave.getDate()));
        if(alreadyExists){
            asset.getAssetValues().stream().filter(valueItem -> valueItem.getDate().equals(itemToSave.getDate())).findFirst().get().setValue(itemToSave.getValue());
        }else{
            asset.getAssetValues().add(itemToSave);
        }
        realmManager.insertOrUpdateAsset(asset);
    }

    public void insertOrUpdateDebtValueItem(ValueItem itemToSave, String debtName){
        Debt debt = realmManager.getDebt(debtName);
        boolean alreadyExists = debt.getDebtValues().stream().anyMatch(item -> item.getDate().equals(itemToSave.getDate()));
        if(alreadyExists){
            debt.getDebtValues().stream().filter(valueItem -> valueItem.getDate().equals(itemToSave.getDate())).findFirst().get().setValue(itemToSave.getValue());
        }else{
            debt.getDebtValues().add(itemToSave);
        }
        realmManager.insertOrUpdateDebt(debt);
    }

    public void updateAsset(ValueItem newValue, String assetName) {
        realmManager.updateAsset(newValue, assetName);
    }

    public void updateAsset(String assetName, String assetCategory, String previousName) {
        realmManager.updateAsset(assetName, assetCategory, previousName);
    }

    public void updateDebt(ValueItem newValue, String debtName) {
        realmManager.updateDebt(newValue, debtName);
    }

    public void updateDebt(String debtName, String categoryName, String previousName) {
        realmManager.updateDebt(debtName, categoryName, previousName);
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
