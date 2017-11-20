package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import java.util.List;

/**
 * Created by jbrannen on 11/19/17.
 */

public class MainActivityModel {
    private List<Integer> netWorths;
    private List<Integer> assets;
    private List<Integer> debts;
    private List<String> dates;

    public List<Integer> getNetWorths() {
        return netWorths;
    }

    public void setNetWorths(List<Integer> netWorths) {
        this.netWorths = netWorths;
    }

    public List<Integer> getAssets() {
        return assets;
    }

    public void setAssets(List<Integer> assets) {
        this.assets = assets;
    }

    public List<Integer> getDebts() {
        return debts;
    }

    public void setDebts(List<Integer> debts) {
        this.debts = debts;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
