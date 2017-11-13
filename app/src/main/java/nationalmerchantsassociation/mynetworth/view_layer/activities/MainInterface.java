package nationalmerchantsassociation.mynetworth.view_layer.activities;

/**
 * Created by jbrannen on 11/7/17.
 */

public interface MainInterface {
    void setData(float assets, float debts);
    void onNetWorthReceived(int netWorthValue, int previousValue);
}
