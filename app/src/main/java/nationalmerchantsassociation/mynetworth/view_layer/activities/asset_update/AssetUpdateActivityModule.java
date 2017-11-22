package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;


import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetUpdateActivityModule {

    @Provides
    AssetUpdateView provideAssetUpdateView(AssetUpdateActivity assetUpdateActivity){
        return assetUpdateActivity;
    }


    @Provides
    AssetUpdatePresenter provideAssetUpdatePresenter(AssetUpdateView assetUpdateView, Realm mainUiRealm, DataManager dataManager){
        return new AssetUpdatePresenterImp(assetUpdateView, mainUiRealm, dataManager);
    }
}