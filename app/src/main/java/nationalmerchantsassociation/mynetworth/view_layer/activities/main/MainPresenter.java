package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import io.realm.Realm;
import io.realm.RealmResults;

import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.utils.LineChartDataMapper;
import nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil;

/**
 * Created by jbrannen on 11/7/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private Realm realm;
    private RealmResults<Asset> assets;
    private RealmResults<Debt> debts;
    private MainActivityModel model;
    private int previousNetWorth;//This value is needed for knowing what value to start the netWorth animation at.

    public MainPresenter(MainContract.View mainView, Realm mainUiRealm) {
        this.view = mainView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        previousNetWorth = 0;
        assets = realm.where(Asset.class).findAll();
        debts = realm.where(Debt.class).findAll();
        assets.addChangeListener(assets -> createModel(assets, debts));
        debts.addChangeListener(debts -> createModel(assets, debts));
        createModel(assets, debts);
    }

    private void createModel(RealmResults<Asset> assets, RealmResults<Debt> debts){
        model = LineChartDataMapper.mapHistoricalNetWorth(assets, debts, LineChartDataMapper.MONTHS_6);
        calculateNetWorth(model.getAssets().get(5), model.getDebts().get(5), 1500);
        setBarChartData(model.getAssets().get(5), model.getDebts().get(5), false);
        view.setLineChartData(model.getNetWorths());
        view.setLineChartTitle(buildLineChartTitle("6M"));
    }

    private String buildLineChartTitle(String range){
        double netChange = (model.getAssets().get(5)-model.getDebts().get(5))-(model.getAssets().get(0)-model.getDebts().get(0));
        return TextFormatterUtil.buildLineChartTitle(range, (int)netChange, (model.getAssets().get(5)-model.getDebts().get(5)));
    }

    @Override
    public void updateModelForRange(int range){
        createModel(assets, debts);
    }


    private void updateLineChart(String title, int monthCount){
        model = LineChartDataMapper.mapHistoricalNetWorth(assets, debts, monthCount);
        view.setLineChartData(model.getNetWorths());
        view.setLineChartTitle(buildLineChartTitle(title));
    }

    @Override
    public void onSixMonthsSelected() {
        updateLineChart("6M", LineChartDataMapper.MONTHS_6);
    }

    @Override
    public void onOneYearSelected() {
        updateLineChart("1Y", LineChartDataMapper.MONTHS_12);
    }

    @Override
    public void onFiveYearsSelected() {
        updateLineChart("5Y", LineChartDataMapper.YEARS_5);
    }

    @Override
    public void onTenYearsSelected() {
        updateLineChart("10Y", LineChartDataMapper.YEARS_10);
    }

    @Override
    public void onAllSelected() {
        updateLineChart("All", LineChartDataMapper.ALL);
    }

    @Override
    public void onDestroy() {
        assets.removeAllChangeListeners();
        debts.removeAllChangeListeners();
    }

    private void calculateNetWorth(int assets, int debts, long time) {
        formatNetWorth(previousNetWorth, (assets-debts), time);
        previousNetWorth = (assets+debts);
    }

    private void formatNetWorth(int previousNetWorth, int currentNetWorth, long time) {
        if(currentNetWorth == 0){
            view.setStaticNetWorth("     $0     ");
        }else{
            view.setAnimatedNetWorth(previousNetWorth, currentNetWorth, time);
        }
    }

    private void setBarChartData(double assetValueSum, double debtValueSum, boolean animate) {
        if(assetValueSum > 0 || debtValueSum > 0) {
            view.setData((float) assetValueSum, (float) debtValueSum, animate);
        }else{
            view.initEmpyState();
        }
    }

    @Override
    public void onLineChartNotSelected() {
        calculateNetWorth(model.getAssets().get(5), model.getDebts().get(5), 300);
        setBarChartData(model.getAssets().get(5), model.getDebts().get(5), false);
        view.setTitle(model.getDates().get(5));
    }

    @Override
    public void onLineChartValueSelected(float x) {
        calculateNetWorth(model.getAssets().get((int)x), model.getDebts().get((int)x), 300);
        setBarChartData(model.getAssets().get((int)x), model.getDebts().get((int)x), false);
        view.setTitle(model.getDates().get((int)x));
    }
}
