package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_edit;

import java.util.List;

public interface AssetsEditContract {
    interface View{
        void initSpinners(List<String> categories);
        void onUpdate(String assetName);
        void onPostExecute(String assetName);
        void finishActivityForResult(String assetName, int resultCode);
        void initAssetName(String assetName);
        void initAssetCategory(String assetCategory);
    }

    interface Presenter{
        void onCreate(String assetName, String assetCategory);
        void onBackPressed();
        void saveCategorySelection(String assetCategory);
        void initSpinner(String[] stringArray);
        void saveAsset(String assetName);
        void deleteAsset();
    }
}
