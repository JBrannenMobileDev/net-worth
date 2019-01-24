package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

public interface DebtsContract {
    interface View{
        void updateRecycler();
        void initRecycler(RealmResults<Debt> debts);
        void setTitleWithTotal(double sum);
        void setLineChartData(List<Integer> debts);
        void setLineChartTitle(String title);
    }

    interface Presenter{
        void onCreate();
        void onLineChartValueSelected(float x);
        void onLineChartNotSelected();
        void onDestroy();
    }
}
