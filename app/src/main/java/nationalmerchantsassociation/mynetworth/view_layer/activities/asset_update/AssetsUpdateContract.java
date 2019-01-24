package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_update;

import java.util.List;

public interface AssetsUpdateContract {
    interface View{
        void initDateSpinners(List<String> months, List<Integer> years);
        void onUpdate(String assetName);
        void onPostExecute(String assetName);
        void finishActivityForResult(String assetName);
    }

    interface Presenter{
        void onCreate(String assetName);
        void saveMonthSelection(String month);
        void saveYearSelection(Integer year);
        void onUpdate(Double value);
        void onBackPressed();
    }
}
