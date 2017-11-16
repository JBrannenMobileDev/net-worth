package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.MonthListUtil;
import nationalmerchantsassociation.mynetworth.utils.YearListUtil;

/**
 * Created by jbrannen on 11/13/17.
 */

public class CreateAssetPresenterImp implements CreateAssetPresenter {
    private CreateAssetView view;
    private DataManager dataManager;
    private String category;
    private String month;
    private int year;

    public CreateAssetPresenterImp(CreateAssetView createAssetView, DataManager dataManager) {
        this.view = createAssetView;
        this.dataManager = dataManager;
    }

    @Override
    public void initSpinner(String[] assetCategories) {
        List<String> categories = new ArrayList<>(Arrays.asList(assetCategories));
        Collections.sort(categories);
        view.initSpinners(categories);
    }

    @Override
    public void initDateSpinners(){
        view.initDateSpinners(MonthListUtil.generateMonthList(), YearListUtil.generateYearList());
    }

    @Override
    public void saveAsset(String value, String name) {
        Integer intValue = Integer.valueOf(value);
        if(intValue > 0 && !name.isEmpty()) {
            if (category == null || category.isEmpty()) {
                category = "Uncategorized";
            }
            dataManager.insertOrUpdateAsset(new Asset(intValue, name, category, month, year));
            view.finish();
        }else{
            if(intValue == 0){
                view.createValueErrorToast();
            }else if(name == null || name.isEmpty() || name.equalsIgnoreCase("")){
                view.createNameErrorToast();
            }
        }
    }

    @Override
    public void saveCategorySelection(String category){
            this.category = category;
    }

    @Override
    public void saveMonthSelection(String month){
        this.month = month;
    }

    @Override
    public void saveYearSelection(int year){
        this.year = year;
    }
}
