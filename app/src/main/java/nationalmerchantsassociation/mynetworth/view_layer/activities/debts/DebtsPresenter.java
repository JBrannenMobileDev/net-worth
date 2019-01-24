package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import io.realm.Realm;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class DebtsPresenter implements DebtsContract.Presenter {
    private DebtsContract.View view;
    private Realm realm;
    private RealmResults<Debt> debts;
    private DebtsModel model;

    public DebtsPresenter(DebtsContract.View debtsView, Realm mainUiRealm) {
        this.view = debtsView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDestroy() {
        debts.removeAllChangeListeners();
    }

    @Override
    public void onLineChartValueSelected(float x) {
        view.setTitleWithTotal(model.getDebts().get((int)x));
    }

    @Override
    public void onLineChartNotSelected() {
        setDefaultValues();
    }

    private void initData() {
        debts = realm.where(Debt.class).findAllSorted("name");
        debts.addChangeListener(debts1 -> view.updateRecycler());
        view.initRecycler(debts);
        createModel(debts);
    }

    private void createModel(RealmResults<Debt> debts) {
        model = LineChartDataMapper.mapDebtsModel(debts, LineChartDataMapper.MONTHS_6);
        view.setLineChartData(model.getDebts());
        setDefaultValues();
    }

    private void setDefaultValues(){
        view.setTitleWithTotal(model.getDebts().get(5));
        view.setLineChartTitle(TextFormatterUtil.buildLineChartTitle("6M",
                (model.getDebts().get(5))-(model.getDebts().get(0)), model.getDebts().get(5)));
    }
}
