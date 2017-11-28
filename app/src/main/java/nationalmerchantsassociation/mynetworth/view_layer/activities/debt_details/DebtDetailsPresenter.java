package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtDetailsPresenter {
    void onResume(String debtName);
    void onDebtValueSelected(String date);
    void onLineChartValueSelected(float x);
    void onLineChartNotSelected();
    void onUpdateClicked();
    void onActivityResult(String debtName);
    void onEditSelected();
}
