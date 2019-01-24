package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import java.util.List;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

public interface DebtDetailsContract {
    interface View{
        void initRecycler(List<ValueItem> debtValues);
        void updateRecycler();
        void setTitleWithTotal(double sum, String debt);
        void startEditActivity(String debtName, String date);
        void setLineChartData(List<Integer> debtValues);
        void setLineChartTitle(String title);
        void highlightSelectedItem(String date);
        void startUpdateActivity(String debtName);
    }

    interface Presenter{
        void onResume(String debtName);
        void onDebtValueSelected(String date);
        void onLineChartValueSelected(float x);
        void onLineChartNotSelected();
        void onUpdateClicked();
        void onActivityResult(String debtName);
        void onEditSelected();
    }
}
