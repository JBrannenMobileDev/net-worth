package nationalmerchantsassociation.mynetworth.view_layer.presenters;

import java.text.DecimalFormat;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.view_layer.activities.AssetDetailsInterface;

/**
 * Created by jbrannen on 11/10/17.
 */

public class AssetDetailsPresenter implements AssetDetailsPresenterInterface {

    private AssetDetailsInterface view;
    private Realm realm;
    private Asset asset;
    private String category;

    public AssetDetailsPresenter(AssetDetailsInterface view) {
        this.view = view;
    }

    @Override
    public void onDelete() {
        view.launchAreYouSureDialog(asset.getName());
    }

    @Override
    public void initData(String assetName) {
        asset = realm.where(Asset.class).equalTo("name", assetName).findFirst();
        category = asset.getCategory();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        view.setValueEt("$" + formatter.format(asset.getMarketValue()));
        view.setSavedCategoryName(asset.getCategory());
    }

    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        realm.close();
    }

    @Override
    public void SpinnerItemSelected(String categoryName) {
        category = categoryName;
    }

    @Override
    public void onSave() {
        view.onSave(category);
    }
}
