package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

/**
 * Created by jbrannen on 11/13/17.
 */

public interface CreateAssetPresenter {
    void initSpinner(String[] stringArray);
    void saveAsset(String value, String name);
    void saveCategorySelection(String category);
}