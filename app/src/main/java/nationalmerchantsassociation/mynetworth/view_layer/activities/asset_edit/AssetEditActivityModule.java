package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;


import dagger.Module;
import dagger.Provides;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetEditActivityModule {

    @Provides
    AssetsEditContract.View provideAssetUpdateView(AssetEditActivity assetUpdateActivity){
        return assetUpdateActivity;
    }


    @Provides
    AssetsEditContract.Presenter provideAssetUpdatePresenter(AssetsEditContract.View assetUpdateView, DataManager dataManager){
        return new AssetEditPresenter(assetUpdateView, dataManager);
    }
}