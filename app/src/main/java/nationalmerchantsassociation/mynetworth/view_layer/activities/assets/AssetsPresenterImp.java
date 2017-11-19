package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public class AssetsPresenterImp implements AssetsPresenter {
    private AssetsView view;
    private Realm realm;

    public AssetsPresenterImp(AssetsView assetsView, Realm mainUiRealm) {
        this.view = assetsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        initData();
    }

    private void initData() {
        RealmResults<Asset> assets = realm.where(Asset.class).findAllSorted("name");
        assets.addChangeListener(assetsRealtime -> view.updateRecycler());
        double sum = assets.stream().mapToDouble(asset -> asset.getCurrentValueItem().getValue()).sum();
        view.initRecycler(assets);
        view.setTitleWithTotal(sum);
    }
}
