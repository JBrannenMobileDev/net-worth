package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

public interface AssetsContract {
    interface View{
        void initRecycler(RealmResults<Asset> assets);
        void updateRecycler();
        void setTitleWithTotal(double sum);
        void setLineChartTitle(String title);
        void setLineChartData(List<Integer> assets);
    }

    interface Presenter{
        void onCreate();
        void onLineChartValueSelected(float x);
        void onLineChartNotSelected();
        void onDestroy();
    }
}
