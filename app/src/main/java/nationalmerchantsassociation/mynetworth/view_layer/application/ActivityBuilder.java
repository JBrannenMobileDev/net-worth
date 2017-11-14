package nationalmerchantsassociation.mynetworth.view_layer.application;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details.AssetDetailsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details.AssetDetailsActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.assets.AssetsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.assets.AssetsActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset.CreateAssetActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset.CreateAssetActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt.CreateDebtActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt.CreateDebtActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details.DebtDetailsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details.DebtDetailsActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debts.DebtsActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.debts.DebtsActivityModule;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.MainActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.MainActivityModule;

/**
 * Created by jbrannen on 11/13/17.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = AssetsActivityModule.class)
    abstract AssetsActivity bindAssetsActivity();

    @ContributesAndroidInjector(modules = DebtsActivityModule.class)
    abstract DebtsActivity bindDebtsActivity();

    @ContributesAndroidInjector(modules = AssetDetailsActivityModule.class)
    abstract AssetDetailsActivity bindAssetDetailsActivity();

    @ContributesAndroidInjector(modules = DebtDetailsActivityModule.class)
    abstract DebtDetailsActivity bindDebtDetailsActivity();

    @ContributesAndroidInjector(modules = CreateAssetActivityModule.class)
    abstract CreateAssetActivity bindAssetActivity();

    @ContributesAndroidInjector(modules = CreateDebtActivityModule.class)
    abstract CreateDebtActivity bindCreateDebtActivity();
}
