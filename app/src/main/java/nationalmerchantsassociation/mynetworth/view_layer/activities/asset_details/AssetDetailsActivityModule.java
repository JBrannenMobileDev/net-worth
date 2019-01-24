package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetDetailsActivityModule {

    @Provides
    AssetDetailsContract.View provideAssetsView(AssetDetailsActivity assetsActivity){
        return assetsActivity;
    }

    @Provides
    AssetDetailsContract.Presenter provideAssetsPresenter(AssetDetailsContract.View assetsView, Realm mainUiRealm){
        return new AssetDetailsPresenter(assetsView, mainUiRealm);
    }
}