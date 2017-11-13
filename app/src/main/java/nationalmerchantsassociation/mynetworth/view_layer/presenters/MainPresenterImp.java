package nationalmerchantsassociation.mynetworth.view_layer.presenters;

import io.realm.Realm;
import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.view_layer.activities.MainInterface;

/**
 * Created by jbrannen on 11/7/17.
 */

public class MainPresenterImp implements MainPresenter {

    private MainInterface activity;
    private Realm realm;
    private RealmResults<Asset> assets;
    private RealmResults<Debt> debts;
    private int previousNetWorth;

    public MainPresenterImp(MainInterface activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        previousNetWorth = 0;
        realm = Realm.getDefaultInstance();
        assets = realm.where(Asset.class).findAll();
        debts = realm.where(Debt.class).findAll();
        assets.addChangeListener(assets -> calculateNetWorth(assets, debts));
        debts.addChangeListener(debts -> calculateNetWorth(assets, debts));
        calculateNetWorth(assets, debts);
    }

    private void calculateNetWorth(RealmResults<Asset> assets, RealmResults<Debt> debts) {
        double assetValueSum = assets.stream().mapToDouble(Asset::getMarketValue).sum();
        double debtValueSum = debts.stream().mapToDouble(Debt::getValue).sum();
        setBarChartData(assetValueSum, debtValueSum);
        activity.onNetWorthReceived(previousNetWorth, (int)(assetValueSum-debtValueSum));
        previousNetWorth = (int)(assetValueSum+debtValueSum);
    }

    private void setBarChartData(double assetValueSum, double debtValueSum) {
        activity.setData((float)assetValueSum, (float)debtValueSum);
    }

    @Override
    public void onDestroy() {
        assets.removeAllChangeListeners();
        debts.removeAllChangeListeners();
        realm.close();
    }
}
