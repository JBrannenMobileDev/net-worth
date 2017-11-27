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
    AssetEditView provideAssetUpdateView(AssetEditActivity assetUpdateActivity){
        return assetUpdateActivity;
    }


    @Provides
    AssetEditPresenter provideAssetUpdatePresenter(AssetEditView assetUpdateView, DataManager dataManager){
        return new AssetEditPresenterImp(assetUpdateView, dataManager);
    }
}