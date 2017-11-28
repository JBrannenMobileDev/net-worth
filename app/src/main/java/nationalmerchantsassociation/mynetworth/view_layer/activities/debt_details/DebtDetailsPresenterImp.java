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
    private String newDebtName;
    private DebtDetailsModel model;
    private Debt debt;

    public DebtDetailsPresenterImp(DebtDetailsView debtView, Realm mainUiRealm) {
        this.view = debtView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onResume(String debtName) {
        if(newDebtName == null){
            this.debtName = debtName;
        }
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
        view.startUpdateActivity(debtName);
    }

    @Override
    public void onActivityResult(String debtName) {
        this.debtName = debtName;
        this.newDebtName = debtName;
        initData();
    }

    @Override
    public void onEditSelected() {
        view.startEditActivity(debtName, debt.getCategory());
    }

    private void initData() {
        debt = realm.where(Debt.class).equalTo("name", debtName).findFirst();
        if(debt != null) {
            debt.addChangeListener(debtsRealtime -> {
                view.updateRecycler();
                if(debt.isValid()) {
                    setDefaultState();
                }
            });
            view.initRecycler(ValueItemUtil.sortByDate(debt.getDebtValues()));
            setDefaultState();
        }
    }

    private DebtDetailsModel buildDebtDetailsModel(List<ValueItem> debtValues, int range){
        this.model = LineChartDataMapper.mapDebtDetails(debtValues, range);
        return this.model;
    }

    private void setDefaultState(){
        view.setTitleWithTotal(debt.getCurrentValue(), debtName);
        view.setLineChartData(buildDebtDetailsModel(debt.getDebtValues(), LineChartDataMapper.MONTHS_6).getDebtValues());
        view.setLineChartTitle(TextFormatterUtil.buildLineChartTitle("6M",
                (model.getDebtValues().get(5))-(model.getDebtValues().get(0)), model.getDebtValues().get(5)));
    }
}
