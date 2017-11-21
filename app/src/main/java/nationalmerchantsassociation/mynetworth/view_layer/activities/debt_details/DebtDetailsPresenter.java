package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtDetailsPresenter {
    void onCreate(String debtName);
    void onDebtValueSelected(String date);
    void onLineChartValueSelected(float x);
    void onLineChartNotSelected();
}
