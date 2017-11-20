package nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface CreateDebtPresenter {
    void saveCategories(String category);
    void initSpinner(String[] categories);
    void saveAsset(String value, String name);
    void initDateSpinners();
    void saveMonthSelection(String mont);
    void saveYearSelection(Integer year);
}
