package nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt;

import dagger.Module;
import dagger.Provides;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class CreateDebtActivityModule {

    @Provides
    CreateDebtView provideCreateDebtView(CreateDebtActivity createDebtActivity){
        return createDebtActivity;
    }

    @Provides
    CreateDebtPresenter provideCreateDebtPresenter(CreateDebtView createDebtView, DataManager dataManager){
        return new CreateDebtPresenterImp(createDebtView, dataManager);
    }
}