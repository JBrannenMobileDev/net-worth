package nationalmerchantsassociation.mynetworth.view_layer.activities.assets;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public class AssetsModel {
    private List<Integer> assets;
    private List<String> dates;

    public List<Integer> getAssets() {
        return assets;
    }

    public void setAssets(List<Integer> assets) {
        this.assets = assets;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
