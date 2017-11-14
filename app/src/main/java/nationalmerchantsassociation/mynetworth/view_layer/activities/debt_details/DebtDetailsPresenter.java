package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;


/**
 * Created by jbrannen on 11/10/17.
 */

public interface DebtDetailsPresenter {
    void onDelete();
    void onCreate(String debtName);
    void onDestroy();
    void SpinnerItemSelected(String categoryName);
    void onSave(String value);
    void initSpinner(String[] stringArray);
    void onDeleteDebt(String debtName);
}
