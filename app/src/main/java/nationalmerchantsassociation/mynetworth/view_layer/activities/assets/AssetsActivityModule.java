package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AssetsActivityModule {

    @Provides
    AssetsView provideAssetsView(AssetsActivity assetsActivity){
        return assetsActivity;
    }

    @Provides
    AssetsPresenter provideAssetsPresenter(AssetsView assetsView, Realm mainUiRealm){
        return new AssetsPresenterImp(assetsView, mainUiRealm);
    }
}