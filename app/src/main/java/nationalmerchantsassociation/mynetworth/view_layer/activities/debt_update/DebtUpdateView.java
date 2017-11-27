package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface DebtUpdateView {
    void initDateSpinners(List<String> months, List<Integer> years);
    void onUpdate(String debtName);
    void onPostExecute(String debtName);
    void finishActivityForResult(String debtName);
}
