package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/20/17.
 */

public class DebtDetailsModel {
    private List<ValueItem> valueItemList;
    private List<Integer> debtValues;
    private List<String> dates;

    public List<ValueItem> getValueItemList() {
        return valueItemList;
    }

    public void setValueItemList(List<ValueItem> valueItemList) {
        this.valueItemList = valueItemList;
    }

    public List<Integer> getDebtValues() {
        return debtValues;
    }

    public void setDebtValues(List<Integer> debtValues) {
        this.debtValues = debtValues;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
