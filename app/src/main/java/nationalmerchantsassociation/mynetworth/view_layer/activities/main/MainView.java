package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import java.util.List;

/**
 * Created by jbrannen on 11/7/17.
 */

public interface MainView {
    void setData(float assets, float debts, boolean animate);
    void initEmpyState();
    void setStaticNetWorth(String zero_net_worth);
    void setAnimatedNetWorth(int previousNetWorth, int currentNetWorth, long time);
    void setLineChartData(List<Integer> netWorths);
    void setTitle(String date);
    void setLineChartTitle(String lineChartTitle);
}
