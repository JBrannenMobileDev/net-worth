package nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;
import nationalmerchantsassociation.mynetworth.utils.YearListUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class CreateDebtPresenterImp implements CreateDebtPresenter {
    private CreateDebtView view;
    private DataManager dataManager;
    private String category;
    private String month;
    private int year;

    public CreateDebtPresenterImp(CreateDebtView createDebtView, DataManager dataManager) {
        this.view = createDebtView;
        this.dataManager = dataManager;
    }

    @Override
    public void initSpinner(String[] debtCategories) {
        List<String> categories = new ArrayList<>(Arrays.asList(debtCategories));
        Collections.sort(categories);
        view.initSpinner(categories);
    }

    @Override
    public void saveAsset(String value, String name) {
        Integer amount = Integer.valueOf(value);
        if(amount > 0 && !name.isEmpty() && !name.equalsIgnoreCase("")){
            dataManager.insertOrUpdateDebt(new Debt(amount, name, category, month, year));
            view.finish();
        }else{
            if(amount == 0) {
                view.createValueErrorToast();
            }else if(name == null || name.isEmpty() || name.equalsIgnoreCase("")) {
                view.createNameErrorToast();
            }
        }
    }

    @Override
    public void initDateSpinners() {
        view.initDateSpinners(MonthConversionUtil.generateMonthList(), YearListUtil.generateYearList());
    }

    @Override
    public void saveCategories(String category) {
        this.category = category;
    }

    @Override
    public void saveMonthSelection(String month) {
        this.month = month;
    }

    @Override
    public void saveYearSelection(Integer year) {
        this.year = year;
    }
}
