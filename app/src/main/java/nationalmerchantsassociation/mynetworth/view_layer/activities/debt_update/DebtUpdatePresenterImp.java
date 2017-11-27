package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;

import io.realm.Realm;
import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;
import nationalmerchantsassociation.mynetworth.utils.YearListUtil;

/**
 * Created by jbrannen on 11/22/17.
 */

public class DebtUpdatePresenterImp implements DebtUpdatePresenter {

    private String debtName;
    private DebtUpdateView view;
    private Realm realm;
    private DataManager dataManager;
    private String month;
    private int year;

    public DebtUpdatePresenterImp(DebtUpdateView debtUpdateView, Realm mainUiRealm, DataManager dataManager) {
        this.view = debtUpdateView;
        this.realm = mainUiRealm;
        this.dataManager = dataManager;
    }

    @Override
    public void onCreate(String debtName) {
        this.debtName = debtName;
        view.initDateSpinners(MonthConversionUtil.generateMonthList(), YearListUtil.generateYearList());
    }

    @Override
    public void saveMonthSelection(String month) {
        this.month = month;
    }

    @Override
    public void saveYearSelection(Integer year) {
        this.year = year;
    }

    @Override
    public void onUpdate(Double value) {
        boolean debtValueExists = realm.where(Debt.class).equalTo("name", debtName).
                findFirst().getDebtValues().
                stream().anyMatch(item -> item.getDate().equals(CustomDateFormatter.createDate(month, year)));

        if(!debtValueExists){
            ValueItem newValue = new ValueItem(value , month, year);
            dataManager.updateDebt(newValue, debtName);
            view.onUpdate(debtName);
        }
    }

    @Override
    public void onBackPressed() {
        view.finishActivityForResult(debtName);
    }
}
