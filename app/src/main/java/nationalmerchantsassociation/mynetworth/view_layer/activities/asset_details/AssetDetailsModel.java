package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/20/17.
 */

public class AssetDetailsModel {
    private List<ValueItem> valueItemList;
    private List<Integer> assetValues;
    private List<String> dates;

    public List<ValueItem> getValueItemList() {
        return valueItemList;
    }

    public void setValueItemList(List<ValueItem> valueItemList) {
        this.valueItemList = valueItemList;
    }

    public List<Integer> getAssetValues() {
        return assetValues;
    }

    public void setAssetValues(List<Integer> assetValues) {
        this.assetValues = assetValues;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
