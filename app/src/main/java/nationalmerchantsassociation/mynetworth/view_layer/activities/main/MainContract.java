package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import java.util.List;

public interface MainContract {
    interface View{
        void setData(float assets, float debts, boolean animate);
        void initEmpyState();
        void setStaticNetWorth(String zero_net_worth);
        void setAnimatedNetWorth(int previousNetWorth, int currentNetWorth, long time);
        void setLineChartData(List<Integer> netWorths);
        void setTitle(String date);
        void setLineChartTitle(String lineChartTitle);
    }

    interface Presenter{
        void onCreate();
        void onDestroy();
        void onLineChartNotSelected();
        void onLineChartValueSelected(float x);
        void updateModelForRange(int range);
        void onOneYearSelected();
        void onSixMonthsSelected();
        void onFiveYearsSelected();
        void onTenYearsSelected();
        void onAllSelected();
    }
}
