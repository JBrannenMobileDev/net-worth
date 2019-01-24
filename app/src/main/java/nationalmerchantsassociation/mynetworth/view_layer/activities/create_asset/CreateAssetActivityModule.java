package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import dagger.Module;
import dagger.Provides;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class CreateAssetActivityModule {

    @Provides
    CreateAssetContract.View provideCreateAssetView(CreateAssetActivity createAssetActivity){
        return createAssetActivity;
    }

    @Provides
    CreateAssetContract.Presenter provideCreateAssetPresenter(CreateAssetContract.View createAssetView, DataManager dataManager){
        return new CreateAssetPresenter(createAssetView, dataManager);
    }
}