package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;


import dagger.Module;
import dagger.Provides;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DebtEditActivityModule {

    @Provides
    DebtEditContract.View provideDebtUpdateView(DebtEditActivity debtUpdateActivity){
        return debtUpdateActivity;
    }


    @Provides
    DebtEditContract.Presenter provideDebtUpdatePresenter(DebtEditContract.View debtUpdateView, DataManager dataManager){
        return new DebtEditPresenter(debtUpdateView, dataManager);
    }
}