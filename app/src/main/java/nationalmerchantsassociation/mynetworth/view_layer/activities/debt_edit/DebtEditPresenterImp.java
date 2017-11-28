package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.DataManager;

/**
 * Created by jbrannen on 11/22/17.
 */

public class DebtEditPresenterImp implements DebtEditPresenter {

    private String debtNamePrevious;
    private String debtName;
    private String debtCategory;
    private DebtEditView view;
    private DataManager dataManager;

    public DebtEditPresenterImp(DebtEditView debtUpdateView, DataManager dataManager) {
        this.view = debtUpdateView;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String debtName, String debtCategory) {
        this.debtName = debtName;
        this.debtCategory = debtCategory;
        this.debtNamePrevious = debtName;
        view.initDebtName(debtName);
        view.initDebtCategory(debtCategory);
    }

    @Override
    public void initSpinner(String[] debtCategories) {
        List<String> categories = new ArrayList<>(Arrays.asList(debtCategories));
        Collections.sort(categories);
        view.initSpinners(categories);
    }

    @Override
    public void saveDebt(String debtName) {
        dataManager.updateDebt(debtName, debtCategory, debtNamePrevious);
        view.onPostExecute(debtName);
    }

    @Override
    public void deleteDebt() {
        dataManager.deleteDebt(debtName);
        view.finishActivityForResult(debtName, 2);
    }

    @Override
    public void onBackPressed() {
        view.finishActivityForResult(debtName, 1);
    }

    @Override
    public void saveCategorySelection(String debtCategory) {
        this.debtCategory = debtCategory;
    }
}
