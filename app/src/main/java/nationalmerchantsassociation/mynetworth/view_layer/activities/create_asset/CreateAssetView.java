package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import java.util.List;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface CreateAssetView {
    void initSpinners(List<String> categories);
    void initDateSpinners(List<String> months, List<Integer> years);
    void createValueErrorToast();
    void createNameErrorToast();
    void finish();
}
