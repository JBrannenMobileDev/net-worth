package nationalmerchantsassociation.mynetworth.data_layer.database.realm;

import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 8/29/17.
 */

public class RealmManager {

    private Realm realm;

    public RealmManager(Realm realm) {
        this.realm = realm;
    }

    public List<Asset> getAssets(){
        return realm.where(Asset.class).findAll();
    }

    public List<Debt> getDebts(){
        return realm.where(Debt.class).findAll();
    }

    public void insertOrUpdateDebt(final Debt debt){
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(debt));
    }

    public void insertOrUpdateAsset(final Asset asset){
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(asset));
    }

    public void updateAsset(ValueItem newValue, String assetName){
        realm.executeTransaction(bgRealm -> {
            Asset asset = bgRealm.where(Asset.class).equalTo("name", assetName).findFirst();
            asset.getAssetValues().add(newValue);
            bgRealm.copyToRealmOrUpdate(asset);
        });
    }

    public void updateAsset(String assetName, String assetCategory, String previousName){
        if(!assetName.equals(previousName)) {
            Asset asset = realm.where(Asset.class).equalTo("name", previousName).findFirst();
            Asset newAsset = new Asset();
            newAsset.setName(assetName);
            newAsset.setCategory(assetCategory);
            newAsset.setAssetValues(asset.getAssetValuesRealm());
            insertOrUpdateAsset(newAsset);
            deleteAsset(previousName);
        }else{
            realm.executeTransaction(bgRealm -> {
                Asset asset = bgRealm.where(Asset.class).equalTo("name", assetName).findFirst();
                asset.setCategory(assetCategory);
                bgRealm.copyToRealmOrUpdate(asset);
            });
        }
    }

    public void updateDebt(ValueItem newValue, String debtName){
        realm.executeTransaction(bgRealm -> {
            Debt debt = bgRealm.where(Debt.class).equalTo("name", debtName).findFirst();
            debt.getDebtValues().add(newValue);
            bgRealm.copyToRealmOrUpdate(debt);
        });
    }

    public void updateDebt(String debtName, String categoryName, String previousName){
        if(!debtName.equals(previousName)) {
            Debt debt = realm.where(Debt.class).equalTo("name", previousName).findFirst();
            Debt newDebt = new Debt();
            newDebt.setName(debtName);
            newDebt.setCategory(categoryName);
            newDebt.setDebtValues(debt.getDebtValues());
            insertOrUpdateDebt(newDebt);
            deleteDebt(previousName);
        }else{
            realm.executeTransaction(bgRealm -> {
                Debt debt = bgRealm.where(Debt.class).equalTo("name", debtName).findFirst();
                debt.setCategory(categoryName);
                bgRealm.copyToRealmOrUpdate(debt);
            });
        }
    }

    public void deleteAsset(final String assetName){
        realm.executeTransaction(bgRealm -> {
            Asset assetToDelete = bgRealm.where(Asset.class)
                    .equalTo("name", assetName)
                    .findFirst();
            if(assetToDelete != null){
                assetToDelete.deleteFromRealm();
            }
        });
    }

    public void deleteDebt(final String debtName){
        realm.executeTransaction(bgRealm -> {
            Debt debtToDelete = bgRealm.where(Debt.class)
                    .equalTo("name", debtName)
                    .findFirst();
            if(debtToDelete != null){
                debtToDelete.deleteFromRealm();
            }
        });
    }

    public void nukeDb() {
        realm.close();
        Realm.deleteRealm(Realm.getDefaultConfiguration());
        openRealm();
    }

    private void openRealm(){
        realm = Realm.getDefaultInstance();
    }

    public Asset getAsset(String assetName) {
        return realm.where(Asset.class).equalTo("name", assetName).findFirst();
    }

    public Debt getDebt(String debtName) {
        return realm.where(Debt.class).equalTo("name", debtName).findFirst();
    }
}
