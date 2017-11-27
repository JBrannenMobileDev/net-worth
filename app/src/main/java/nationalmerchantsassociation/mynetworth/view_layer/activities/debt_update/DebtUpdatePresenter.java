package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;

/**
 * Created by jbrannen on 11/22/17.
 */

public interface DebtUpdatePresenter {
    void onCreate(String debtName);
    void saveMonthSelection(String month);
    void saveYearSelection(Integer year);
    void onUpdate(Double value);
    void onBackPressed();
}
