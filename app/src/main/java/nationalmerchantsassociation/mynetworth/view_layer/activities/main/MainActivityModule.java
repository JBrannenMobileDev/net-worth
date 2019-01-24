package nationalmerchantsassociation.mynetworth.view_layer.activities.main;


import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class MainActivityModule {

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    MainContract.Presenter provideMainPresenter(MainContract.View mainView, Realm mainUiRealm){
        return new MainPresenter(mainView, mainUiRealm);
    }
}