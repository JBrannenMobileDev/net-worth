package nationalmerchantsassociation.mynetworth.data_layer.models;

import java.util.List;

/**
 * Created by jbrannen on 11/10/17.
 */

public class MonthlySnapshot {
    private List<Asset> assets;
    private List<Debt> debts;
    private long timestamp;

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
