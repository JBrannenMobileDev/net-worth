package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class AssetDetailsPresenterImp implements AssetDetailsPresenter {
    private static final String EMPTY_STRING = "";
    private AssetDetailsView view;
    private Realm realm;
    private String assetName;
    private AssetDetailsModel model;
    private Asset asset;

    public AssetDetailsPresenterImp(AssetDetailsView assetsView, Realm mainUiRealm) {
        this.view = assetsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate(String assetName) {
        this.assetName = assetName;
        initData();
    }

    @Override
    public void onAssetValueSelected(String date) {
        view.startEditActivity(assetName, date);
    }

    @Override
    public void onLineChartValueSelected(float x) {
        view.highlightSelectedItem(model.getValueItemList().get((int)x).getDate());

    }

    @Override
    public void onLineChartNotSelected() {
        view.highlightSelectedItem(EMPTY_STRING);
    }

    private void initData() {
        asset = realm.where(Asset.class).equalTo("name", assetName).findFirst();
        asset.addChangeListener(assetsRealtime -> view.updateRecycler());
        view.initRecycler(asset.getAssetValues());
        view.setTitleWithTotal(asset.getCurrentValue(), assetName);
        view.setLineChartData(buildAssetDetailsModel(asset.getAssetValues(), LineChartDataMapper.MONTHS_6).getAssetValues());
        view.setLineChartTitle(buildLineChartTitle("6M"));
    }

    private AssetDetailsModel buildAssetDetailsModel(List<ValueItem> assetValues, int range){
        this.model = LineChartDataMapper.mapAssetDetails(assetValues, range);
        return this.model;
    }

    private String buildLineChartTitle(String range){
        int netChange = (model.getAssetValues().get(5))-(model.getAssetValues().get(0));
        try {
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + "(" + netChange / (model.getAssetValues().get(5)) * 100 + "%) Past " + range;
        }catch(ArithmeticException div_zero){
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + " Past " + range;
        }
    }
}
