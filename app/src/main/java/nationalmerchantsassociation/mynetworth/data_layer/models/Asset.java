package nationalmerchantsassociation.mynetworth.data_layer.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jbrannen on 11/7/17.
 */

public class Asset extends RealmObject{
    @PrimaryKey
    private String name;
    private double value;
    private String category;
    private String month;
    private int year;

    public Asset() {
    }

    public Asset(double marketValue, String description, String categroy, String month, int year) {
        this.value = marketValue;
        this.name = description;
        this.category = categroy;
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
