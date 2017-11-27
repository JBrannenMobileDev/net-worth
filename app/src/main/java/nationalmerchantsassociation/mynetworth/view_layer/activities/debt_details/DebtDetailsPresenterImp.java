package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.util.List;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;
import nationalmerchantsassociation.mynetworth.utils.ValueItemUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class DebtDetailsPresenterImp implements DebtDetailsPresenter {
    private static final String EMPTY_STRING = "";
    private DebtDetailsView view;
    private Realm realm;
    private String debtName;
    private DebtDetailsModel model;
    private Debt debt;

    public DebtDetailsPresenterImp(DebtDetailsView debtView, Realm mainUiRealm) {
        this.view = debtView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onResume(String debtName) {
        this.debtName = debtName;
        initData();
    }

    @Override
    public void onDebtValueSelected(String date) {
        view.startEditActivity(debtName, date);
    }

    @Override
    public void onLineChartValueSelected(float x) {
        view.highlightSelectedItem(model.getDates().get((int)x));

    }

    @Override
    public void onLineChartNotSelected() {
        view.highlightSelectedItem(EMPTY_STRING);
    }

    @Override
    public void onUpdateClicked() {
        view.launchUpdateActivity(debtName);
    }

    @Override
    public void onActivityResult(String debtName) {
        this.debtName = debtName;
        initData();
    }

    private void initData() {
        debt = realm.where(Debt.class).equalTo("name", debtName).findFirst();
        if(debt != null) {
            debt.addChangeListener(debtsRealtime -> view.updateRecycler());
            view.initRecycler(ValueItemUtil.sortByDate(debt.getDebtValues()));
            view.setTitleWithTotal(debt.getCurrentValue(), debtName);
            view.setLineChartData(buildDebtDetailsModel(debt.getDebtValues(), LineChartDataMapper.MONTHS_6).getDebtValues());
            view.setLineChartTitle(buildLineChartTitle("6M"));
        }
    }

    private DebtDetailsModel buildDebtDetailsModel(List<ValueItem> debtValues, int range){
        this.model = LineChartDataMapper.mapDebtDetails(debtValues, range);
        return this.model;
    }

    private String buildLineChartTitle(String range){
        int netChange = (model.getDebtValues().get(5))-(model.getDebtValues().get(0));
        try {
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + "(" + netChange / (model.getDebtValues().get(5)) * 100 + "%) Past " + range;
        }catch(ArithmeticException div_zero){
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + " Past " + range;
        }
    }
}
