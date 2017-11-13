package nationalmerchantsassociation.mynetworth.view_layer.presenters;


/**
 * Created by jbrannen on 11/10/17.
 */

public interface DebtDetailsPresenterInterface {
    void onDelete();
    void initData(String assetName);
    void onCreate();
    void onDestroy();
    void SpinnerItemSelected(String categoryName);
    void onSave();
}
