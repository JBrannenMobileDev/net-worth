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
    AssetDetailsView provideAssetsView(AssetDetailsActivity assetsActivity){
        return assetsActivity;
    }

    @Provides
    AssetDetailsPresenter provideAssetsPresenter(AssetDetailsView assetsView, Realm mainUiRealm){
        return new AssetDetailsPresenterImp(assetsView, mainUiRealm);
    }
}