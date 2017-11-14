package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import java.util.List;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface CreateAssetView {
    void initSpinner(List<String> categories);
    void createValueErrorToast();
    void createNameErrorToast();
    void finish();
}
