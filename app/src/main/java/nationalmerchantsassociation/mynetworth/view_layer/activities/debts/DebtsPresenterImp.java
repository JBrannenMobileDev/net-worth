package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import io.realm.Realm;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/13/17.
 */

public class DebtsPresenterImp implements DebtsPresenter {
    private DebtsView view;
    private Realm realm;

    public DebtsPresenterImp(DebtsView debtsView, Realm mainUiRealm) {
        this.view = debtsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        initData();
    }

    private void initData() {
        RealmResults<Debt> debts = realm.where(Debt.class).findAllSorted("name");
        debts.addChangeListener(debts1 -> view.updateRecycler());
        double sum = debts.stream().mapToDouble(debt -> debt.getCurrentValueItem().getValue()).sum();
        view.initRecycler(debts);
        view.setTitleWithTotal(sum);
    }
}
