package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import java.util.List;

/**
 * Created by jbrannen on 11/7/17.
 */

public interface MainView {
    void setData(float assets, float debts);
    void initEmpyState();
    void setStaticNetWorth(String zero_net_worth);
    void setAnimatedNetWorth(int previousNetWorth, int currentNetWorth);
    void setLineChartData(List<Integer> netWorths);
}
