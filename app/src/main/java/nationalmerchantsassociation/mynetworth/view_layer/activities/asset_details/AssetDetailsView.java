package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface AssetDetailsView {
    void initRecycler(RealmResults<Asset> assets);
    void updateRecycler();
    void setTitleWithTotal(double sum);
}
