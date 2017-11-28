package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtDetailsView {
    void initRecycler(List<ValueItem> debtValues);
    void updateRecycler();
    void setTitleWithTotal(double sum, String debt);
    void startEditActivity(String debtName, String date);
    void setLineChartData(List<Integer> debtValues);
    void setLineChartTitle(String title);
    void highlightSelectedItem(String date);
    void startUpdateActivity(String debtName);
}
