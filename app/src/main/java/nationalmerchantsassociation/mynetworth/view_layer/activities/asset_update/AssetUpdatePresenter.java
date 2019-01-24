package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;
import nationalmerchantsassociation.mynetworth.utils.YearListUtil;

/**
 * Created by jbrannen on 11/22/17.
 */

public class AssetUpdatePresenter implements AssetsUpdateContract.Presenter{

    private String assetName;
    private AssetsUpdateContract.View view;
    private Realm realm;
    private DataManager dataManager;
    private String month;
    private int year;

    public AssetUpdatePresenter(AssetsUpdateContract.View assetUpdateView, Realm mainUiRealm, DataManager dataManager) {
        this.view = assetUpdateView;
        this.realm = mainUiRealm;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String assetName) {
        this.assetName = assetName;
        view.initDateSpinners(MonthConversionUtil.generateMonthList(), YearListUtil.generateYearList());
    }

    @Override
    public void saveMonthSelection(String month) {
        this.month = month;
    }

    @Override
    public void saveYearSelection(Integer year) {
        this.year = year;
    }

    @Override
    public void onUpdate(Double value) {
        boolean assetValueExists = realm.where(Asset.class).equalTo("name", assetName).
                findFirst().getAssetValues().
                stream().anyMatch(item -> item.getDate().equals(CustomDateFormatter.createDate(month, year)));

        if(!assetValueExists){
            ValueItem newValue = new ValueItem(value , month, year);
            dataManager.updateAsset(newValue, assetName);
            view.onUpdate(assetName);
        }
    }

    @Override
    public void onBackPressed() {
        view.finishActivityForResult(assetName);
    }
}
