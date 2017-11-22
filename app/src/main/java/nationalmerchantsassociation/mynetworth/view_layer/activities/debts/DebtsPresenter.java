package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtsPresenter {
    void onCreate();
    void onLineChartValueSelected(float x);
    void onLineChartNotSelected();
    void onDestroy();
}
