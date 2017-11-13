package nationalmerchantsassociation.mynetworth.data_layer.models;

import java.util.List;

/**
 * Created by jbrannen on 11/10/17.
 */

public class HistoricalNetWorth {
    private List<MonthlySnapshot> months;

    public List<MonthlySnapshot> getMonths() {
        return months;
    }

    public void setMonths(List<MonthlySnapshot> months) {
        this.months = months;
    }
}
