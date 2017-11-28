package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface DebtEditPresenter {
    void onCreate(String debtName, String assetCategory);
    void onBackPressed();
    void saveCategorySelection(String debtCategory);
    void initSpinner(String[] stringArray);
    void saveDebt(String debtName);
    void deleteDebt();
}
