package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.util.List;

/**
 * Created by jbrannen on 11/10/17.
 */

public interface DebtDetailsView {
    void launchAreYouSureDialog(String debtName);
    void setValueEt(String value);
    void onSave();
    void setSavedCategoryName(String category);
    void initSpinner(List<String> categories);
}
