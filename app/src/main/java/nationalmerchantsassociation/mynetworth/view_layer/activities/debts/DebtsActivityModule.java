package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class DebtsActivityModule {

    @Provides
    DebtsView provideDebtsView(DebtsActivity debtsActivity){
        return debtsActivity;
    }

    @Provides
    DebtsPresenter provideDebtsPresenter(DebtsView debtsView, Realm mainUiRealm){
        return new DebtsPresenterImp(debtsView, mainUiRealm);
    }
}