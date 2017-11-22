package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtsView {
    void updateRecycler();
    void initRecycler(RealmResults<Debt> debts);
    void setTitleWithTotal(double sum);
    void setLineChartData(List<Integer> debts);
    void setLineChartTitle(String title);
}
