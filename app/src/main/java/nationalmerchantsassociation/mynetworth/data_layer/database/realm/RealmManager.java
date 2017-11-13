package nationalmerchantsassociation.mynetworth.data_layer.database.realm;

import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 8/29/17.
 */

public class RealmManager {

    private Realm realm;

    public RealmManager() {
        realm = Realm.getDefaultInstance();
    }

    public List<Asset> getAssets(){
        return realm.where(Asset.class).findAll();
    }

    public List<Debt> getDebtss(){
        return realm.where(Debt.class).findAll();
    }

    public void insertOrUpdateDebt(final Debt debt){
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(debt));
    }

    public void insertOrUpdateAsset(final Asset asset){
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(asset));
    }

    public void deleteAsset(final String assetName){
        realm.executeTransaction(bgRealm -> {
            Asset assetToDelete = bgRealm.where(Asset.class)
                    .equalTo("description", assetName)
                    .findFirst();
            if(assetToDelete != null){
                assetToDelete.deleteFromRealm();
            }
        });
    }

    public void deleteDebt(final String debtName){
        realm.executeTransaction(bgRealm -> {
            Debt debtToDelete = bgRealm.where(Debt.class)
                    .equalTo("description", debtName)
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
}
