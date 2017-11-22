package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import io.realm.Realm;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class AssetsPresenterImp implements AssetsPresenter {
    private AssetsView view;
    private Realm realm;
    private AssetsModel model;
    private RealmResults<Asset> assets;

    public AssetsPresenterImp(AssetsView assetsView, Realm mainUiRealm) {
        this.view = assetsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDestroy() {
        assets.removeAllChangeListeners();
    }

    @Override
    public void onLineChartValueSelected(float x) {
        view.setTitleWithTotal(model.getAssets().get((int)x));
    }

    @Override
    public void onLineChartNotSelected() {
        setDefaultValues();
    }

    private void initData() {
        assets = realm.where(Asset.class).findAllSorted("name");
        assets.addChangeListener(assetsRealtime -> view.updateRecycler());
        view.initRecycler(assets);
        createModel(assets);
    }

    private void createModel(RealmResults<Asset> assets) {
        model = LineChartDataMapper.mapAssetsModel(assets, LineChartDataMapper.MONTHS_6);
        view.setLineChartData(model.getAssets());
        setDefaultValues();
    }

    private void setDefaultValues(){
        view.setTitleWithTotal(model.getAssets().get(5));
        view.setLineChartTitle(TextFormatterUtil.buildLineChartTitle("6M",
                (model.getAssets().get(5))-(model.getAssets().get(0)), model.getAssets().get(5)));
    }
}
