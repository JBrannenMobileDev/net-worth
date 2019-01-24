package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_edit;

import java.util.List;

public interface DebtEditContract {
    interface View{
        void initSpinners(List<String> categories);
        void onUpdate(String debtName);
        void onPostExecute(String debtName);
        void finishActivityForResult(String debtName, int resultCode);
        void initDebtName(String debtName);
        void initDebtCategory(String debtCategory);
    }

    interface Presenter{
        void onCreate(String debtName, String assetCategory);
        void onBackPressed();
        void saveCategorySelection(String debtCategory);
        void initSpinner(String[] stringArray);
        void saveDebt(String debtName);
        void deleteDebt();
    }
}
