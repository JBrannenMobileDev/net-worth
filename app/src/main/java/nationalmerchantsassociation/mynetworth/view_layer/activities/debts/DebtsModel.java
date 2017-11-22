package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public class DebtsModel {
    private List<Integer> debts;
    private List<String> dates;

    public List<Integer> getDebts() {
        return debts;
    }

    public void setDebts(List<Integer> assets) {
        this.debts = assets;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
