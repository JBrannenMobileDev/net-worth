package nationalmerchantsassociation.mynetworth.view_layer.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nationalmerchantsassociation.mynetworth.utils.LineChartUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    LineChartUtil providesLineChartUtil(){
        return new LineChartUtil();
    }
}