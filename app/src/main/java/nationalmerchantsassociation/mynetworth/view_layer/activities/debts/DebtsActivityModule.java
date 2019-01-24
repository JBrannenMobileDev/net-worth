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
    DebtsContract.View provideDebtsView(DebtsActivity debtsActivity){
        return debtsActivity;
    }

    @Provides
    DebtsContract.Presenter provideDebtsPresenter(DebtsContract.View debtsView, Realm mainUiRealm){
        return new DebtsPresenter(debtsView, mainUiRealm);
    }
}