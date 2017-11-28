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
    DebtEditView provideDebtUpdateView(DebtEditActivity debtUpdateActivity){
        return debtUpdateActivity;
    }


    @Provides
    DebtEditPresenter provideDebtUpdatePresenter(DebtEditView debtUpdateView, DataManager dataManager){
        return new DebtEditPresenterImp(debtUpdateView, dataManager);
    }
}