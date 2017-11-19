package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public class AssetDetailsPresenterImp implements AssetDetailsPresenter {
    private AssetDetailsView view;
    private Realm realm;

    public AssetDetailsPresenterImp(AssetDetailsView assetsView, Realm mainUiRealm) {
        this.view = assetsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        initData();
    }

    private void initData() {
        RealmResults<Asset> assets = realm.where(Asset.class).findAllSorted("value", Sort.DESCENDING);
        assets.addChangeListener(assetsRealtime -> view.updateRecycler());
        double sum = assets.stream().mapToDouble(asset -> asset.getCurrentValueItem().getValue()).sum();
        view.initRecycler(assets);
        view.setTitleWithTotal(sum);
    }
}
