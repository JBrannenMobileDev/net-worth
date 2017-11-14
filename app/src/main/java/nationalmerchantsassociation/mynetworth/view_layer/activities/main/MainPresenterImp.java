package nationalmerchantsassociation.mynetworth.view_layer.activities.main;

import io.realm.Realm;
import io.realm.RealmResults;

import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

/**
 * Created by jbrannen on 11/7/17.
 */

public class MainPresenterImp implements MainPresenter {

    private MainView view;
    private Realm realm;
    private RealmResults<Asset> assets;
    private RealmResults<Debt> debts;
    private int previousNetWorth;

    public MainPresenterImp(MainView mainView, Realm mainUiRealm) {
        this.view = mainView;
        this.realm = mainUiRealm;
    }

    @Override
    public void onCreate() {
        previousNetWorth = 0;
        assets = realm.where(Asset.class).findAll();
        debts = realm.where(Debt.class).findAll();
        assets.addChangeListener(assets -> calculateNetWorth(assets, debts));
        debts.addChangeListener(debts -> calculateNetWorth(assets, debts));
        calculateNetWorth(assets, debts);
    }

    private void calculateNetWorth(RealmResults<Asset> assets, RealmResults<Debt> debts) {
        double assetValueSum = assets.stream().mapToDouble(Asset::getValue).sum();
        double debtValueSum = debts.stream().mapToDouble(Debt::getValue).sum();
        setBarChartData(assetValueSum, debtValueSum);
        formatNetWorth(previousNetWorth, (int)(assetValueSum-debtValueSum));
        previousNetWorth = (int)(assetValueSum+debtValueSum);
    }

    private void formatNetWorth(int previousNetWorth, int currentNetWorth) {
        if(currentNetWorth == 0){
            view.setStaticNetWorth("     $0     ");
        }else{
            view.setAnimatedNetWorth(previousNetWorth, currentNetWorth);
        }
    }

    private void setBarChartData(double assetValueSum, double debtValueSum) {
        if(assetValueSum > 0 || debtValueSum > 0) {
            view.setData((float) assetValueSum, (float) debtValueSum);
        }else{
            view.initEmpyState();
        }
    }

    @Override
    public void onDestroy() {
        assets.removeAllChangeListeners();
        debts.removeAllChangeListeners();
    }
}
