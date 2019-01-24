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
    DebtUpdateContract.View provideDebtUpdateView(DebtUpdateActivity debtUpdateActivity){
        return debtUpdateActivity;
    }


    @Provides
    DebtUpdateContract.Presenter provideDebtUpdatePresenter(DebtUpdateContract.View debtUpdateView, Realm mainUiRealm, DataManager dataManager){
        return new DebtUpdatePresenter(debtUpdateView, mainUiRealm, dataManager);
    }
}