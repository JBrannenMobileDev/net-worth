package nationalmerchantsassociation.mynetworth.view_layer.presenters;

import java.text.DecimalFormat;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.view_layer.activities.AssetDetailsInterface;
import nationalmerchantsassociation.mynetworth.view_layer.activities.DebtDetailsInterface;

/**
 * Created by jbrannen on 11/10/17.
 */

public class DebtDetailsPresenter implements DebtDetailsPresenterInterface {

    private DebtDetailsInterface view;
    private Realm realm;
    private Debt debt;
    private String category;

    public DebtDetailsPresenter(DebtDetailsInterface view) {
        this.view = view;
    }

    @Override
    public void onDelete() {
        view.launchAreYouSureDialog(debt.getName());
    }

    @Override
    public void initData(String debtName) {
        debt = realm.where(Debt.class).equalTo("name", debtName).findFirst();
        category = debt.getCategory();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        view.setValueEt("$" + formatter.format(debt.getValue()));
        view.setSavedCategoryName(debt.getCategory());
    }

    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
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
    public void onSave() {
        view.onSave(category);
    }
}
