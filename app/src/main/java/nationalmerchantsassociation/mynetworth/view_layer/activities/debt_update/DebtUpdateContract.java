package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_update;

import java.util.List;

public interface DebtUpdateContract {
    interface View{
        void initDateSpinners(List<String> months, List<Integer> years);
        void onUpdate(String debtName);
        void onPostExecute(String debtName);
        void finishActivityForResult(String debtName);
    }

    interface Presenter{
        void onCreate(String debtName);
        void saveMonthSelection(String month);
        void saveYearSelection(Integer year);
        void onUpdate(Double value);
        void onBackPressed();
    }
}
