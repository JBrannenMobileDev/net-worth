package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;


import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetDetailsActivityModule {

    @Provides
    AssetDetailsView provideAssetDetailsView(AssetDetailsActivity assetDetailsActivity){
        return assetDetailsActivity;
    }


    @Provides
    AssetDetailsPresenter provideAssetDetailsPresenter(AssetDetailsView assetDetailsView, Realm mainUiRealm, DataManager dataManager){
        return new AssetDetailsPresenterImp(assetDetailsView, mainUiRealm, dataManager);
    }
}