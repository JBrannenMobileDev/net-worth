package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface DebtsView {
    void updateRecycler();
    void initRecycler(RealmResults<Debt> debts);
}
