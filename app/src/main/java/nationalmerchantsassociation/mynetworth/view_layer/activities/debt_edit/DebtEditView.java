package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;

import java.util.List;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface DebtEditView {
    void initSpinners(List<String> categories);
    void onUpdate(String debtName);
    void onPostExecute(String debtName);
    void finishActivityForResult(String debtName, int resultCode);
    void initDebtName(String debtName);
    void initDebtCategory(String debtCategory);
}
