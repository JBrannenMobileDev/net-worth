package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface AssetsView {
    void initRecycler(RealmResults<Asset> assets);
    void updateRecycler();
    void setTitleWithTotal(double sum);
    void setLineChartTitle(String title);
    void setLineChartData(List<Integer> assets);
}
