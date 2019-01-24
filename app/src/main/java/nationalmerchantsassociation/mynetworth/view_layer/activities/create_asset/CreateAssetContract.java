package nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset;

import java.util.List;

public interface CreateAssetContract {
    interface View{
        void initSpinners(List<String> categories);
        void initDateSpinners(List<String> months, List<Integer> years);
        void createValueErrorToast();
        void createNameErrorToast();
        void finish();
    }

    interface Presenter{
        void initSpinner(String[] stringArray);
        void initDateSpinners();
        void saveAsset(String value, String name);
        void saveCategorySelection(String category);
        void saveMonthSelection(String month);
        void saveYearSelection(int year);
    }
}
