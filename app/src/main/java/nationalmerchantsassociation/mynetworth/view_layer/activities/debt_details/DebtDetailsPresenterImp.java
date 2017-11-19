package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/10/17.
 */

public class DebtDetailsPresenterImp implements DebtDetailsPresenter {

    private DebtDetailsView view;
    private Realm realm;
    private DataManager dataManager;
    private Debt debt;
    private String category;

    public DebtDetailsPresenterImp(DebtDetailsView debtDetailsView, Realm mainUiRealm, DataManager dataManager) {
        this.view = debtDetailsView;
        this.realm = mainUiRealm;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String debtName) {
        initData(debtName);
    }

    @Override
    public void initSpinner(String[] categoryNames) {
        List<String> categories = new ArrayList<>(Arrays.asList(categoryNames));
        Collections.sort(categories);
        view.initSpinner(categories);
    }

    @Override
    public void onDeleteDebt(String debtName) {
        dataManager.deleteDebt(debtName);
    }

    @Override
    public void onDelete() {
        view.launchAreYouSureDialog(debt.getName());
    }

    private void initData(String debtName) {
        debt = realm.where(Debt.class).equalTo("name", debtName).findFirst();
        category = debt.getCategory();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        view.setValueEt("$" + formatter.format(debt.getCurrentValueItem().getValue()));
        view.setSavedCategoryName(debt.getCategory());
    }

    @Override
    public void onDestroy() {
        realm.close();
    }

    @Override
    public void SpinnerItemSelected(String categoryName) {
        category = categoryName;
    }

    @Override
    public void onSave(String value) {
        Debt debtToSave = new Debt();
        debtToSave.setName(debt.getName());
        debtToSave.getCurrentValueItem().setValue(Double.valueOf(value));
        debtToSave.setCategory(category);
        dataManager.insertOrUpdateDebt(debtToSave);
        view.onSave();
    }
}
