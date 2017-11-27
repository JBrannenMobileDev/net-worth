package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;


import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DebtUpdateActivityModule {

    @Provides
    DebtUpdateView provideDebtUpdateView(DebtUpdateActivity debtUpdateActivity){
        return debtUpdateActivity;
    }


    @Provides
    DebtUpdatePresenter provideDebtUpdatePresenter(DebtUpdateView debtUpdateView, Realm mainUiRealm, DataManager dataManager){
        return new DebtUpdatePresenterImp(debtUpdateView, mainUiRealm, dataManager);
    }
}