package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/22/17.
 */

public class AssetUpdatePresenterImp implements AssetUpdatePresenter{

    private String assetName;
    private AssetUpdateView view;

    public AssetUpdatePresenterImp(AssetUpdateView assetUpdateView, Realm mainUiRealm, DataManager dataManager) {
        this.view = assetUpdateView;
    }

    @Override
    public void onCreate(String assetName) {
        this.assetName = assetName;
    }

    @Override
    public void saveMonthSelection(String month) {

    }

    @Override
    public void saveYearSelection(Integer year) {

    }

    @Override
    public void onUpdate(String value) {

    }

    @Override
    public void onBackPressed() {
        view.finishActivityForResult(assetName);
    }
}
