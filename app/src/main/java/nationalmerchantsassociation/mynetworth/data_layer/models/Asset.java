package nationalmerchantsassociation.mynetworth.data_layer.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jbrannen on 11/7/17.
 */

public class Asset extends RealmObject{
    @PrimaryKey
    private String name;
    private double marketValue;
    private String category;

    public Asset() {
    }

    public Asset(double marketValue, String description, String categroy) {
        this.marketValue = marketValue;
        this.name = description;
        this.category = categroy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }
}