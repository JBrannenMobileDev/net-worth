package nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt;

import java.util.List;

public interface CreateDebtContract {
    interface View{
        void initSpinner(List<String> categories);
        void finish();
        void createValueErrorToast();
        void createNameErrorToast();
        void initDateSpinners(List<String> months, List<Integer> years);
    }

    interface Presenter{
        void saveCategories(String category);
        void initSpinner(String[] categories);
        void saveAsset(String value, String name);
        void initDateSpinners();
        void saveMonthSelection(String mont);
        void saveYearSelection(Integer year);
    }
}
