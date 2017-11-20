package nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt;

import java.util.List;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface CreateDebtView {
    void initSpinner(List<String> categories);
    void finish();
    void createValueErrorToast();
    void createNameErrorToast();
    void initDateSpinners(List<String> months, List<Integer> years);
}
