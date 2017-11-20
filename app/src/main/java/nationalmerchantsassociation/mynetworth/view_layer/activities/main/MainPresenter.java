package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

/**
 * Created by jbrannen on 11/7/17.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void onLineChartNotSelected();
    void onLineChartValueSelected(float x);
    void updateModelForRange(int range);
}
