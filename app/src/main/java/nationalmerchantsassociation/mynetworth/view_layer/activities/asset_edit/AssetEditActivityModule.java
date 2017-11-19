package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;


import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetEditActivityModule {

    @Provides
    AssetEditView provideAssetDetailsView(AssetEditActivity assetDetailsActivity){
        return assetDetailsActivity;
    }


    @Provides
    AssetEditPresenter provideAssetDetailsPresenter(AssetEditView assetDetailsView, Realm mainUiRealm, DataManager dataManager){
        return new AssetEditPresenterImp(assetDetailsView, mainUiRealm, dataManager);
    }
}