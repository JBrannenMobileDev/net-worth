package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

public interface AssetDetailsContract {
    interface View{
        void initRecycler(List<ValueItem> assetValues);
        void updateRecycler();
        void setTitleWithTotal(double sum, String asset);
        void startEditActivity(String assetName, String date);
        void setLineChartData(List<Integer> assetValues);
        void setLineChartTitle(String title);
        void highlightSelectedItem(String date);
        void startUpdateActivity(String assetName);
    }

    interface Presenter{
        void onResume(String assetName);
        void onAssetValueSelected(String date);
        void onLineChartValueSelected(float x);
        void onLineChartNotSelected();
        void onUpdateClicked();
        void onActivityResult(String assetName);
        void onEditSelected();
    }
}
