package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DebtDetailsActivityModule {

    @Provides
    DebtDetailsView provideAssetsView(DebtDetailsActivity debtActivity){
        return debtActivity;
    }

    @Provides
    DebtDetailsPresenter provideDebtPresenter(DebtDetailsView debtView, Realm mainUiRealm){
        return new DebtDetailsPresenterImp(debtView, mainUiRealm);
    }
}