package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DebtDetailsActivityModule {

    @Provides
    DebtDetailsView provideDebtDetailsView(DebtDetailsActivity debtDetailsActivity){
        return debtDetailsActivity;
    }

    @Provides
    DebtDetailsPresenter provideDebtDetailsPresenter(DebtDetailsView debtDetailsView, Realm mainUiRealm, DataManager dataManager){
        return new DebtDetailsPresenterImp(debtDetailsView, mainUiRealm, dataManager);
    }
}