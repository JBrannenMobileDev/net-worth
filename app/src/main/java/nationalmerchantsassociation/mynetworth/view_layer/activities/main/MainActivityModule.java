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
    MainView provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    MainPresenter provideMainPresenter(MainView mainView, Realm mainUiRealm){
        return new MainPresenterImp(mainView, mainUiRealm);
    }
}