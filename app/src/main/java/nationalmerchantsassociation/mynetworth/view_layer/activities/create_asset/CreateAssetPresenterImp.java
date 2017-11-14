package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.DataManager;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public class CreateAssetPresenterImp implements CreateAssetPresenter {
    private CreateAssetView view;
    private DataManager dataManager;
    private String category;

    public CreateAssetPresenterImp(CreateAssetView createAssetView, DataManager dataManager) {
        this.view = createAssetView;
        this.dataManager = dataManager;
    }

    @Override
    public void initSpinner(String[] assetCategories) {
        List<String> categories = new ArrayList<>(Arrays.asList(assetCategories));
        Collections.sort(categories);
        view.initSpinner(categories);
    }

    @Override
    public void saveAsset(String value, String name) {
        Integer intValue = Integer.valueOf(value);
        if(intValue > 0 && !name.isEmpty()) {
            if (category == null || category.isEmpty()) {
                category = "Uncategorized";
            }
            dataManager.insertOrUpdateAsset(new Asset(intValue, name, category));
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
}
