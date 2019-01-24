package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetsActivityModule {

    @Provides
    AssetsContract.View provideAssetsView(AssetsActivity assetsActivity){
        return assetsActivity;
    }

    @Provides
    AssetsContract.Presenter provideAssetsPresenter(AssetsContract.View assetsView, Realm mainUiRealm){
        return new AssetsPresenter(assetsView, mainUiRealm);
    }
}