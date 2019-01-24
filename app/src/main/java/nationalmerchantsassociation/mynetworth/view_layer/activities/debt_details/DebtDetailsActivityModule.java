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
    DebtDetailsContract.View provideAssetsView(DebtDetailsActivity debtActivity){
        return debtActivity;
    }

    @Provides
    DebtDetailsContract.Presenter provideDebtPresenter(DebtDetailsContract.View debtView, Realm mainUiRealm){
        return new DebtDetailsPresenter(debtView, mainUiRealm);
    }
}