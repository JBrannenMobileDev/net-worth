package nationalmerchantsassociation.mynetworth.data_layer.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jbrannen on 11/7/17.
 */

public class Debt extends RealmObject{
    @PrimaryKey
    private String name;
    private double value;
    private String category;

    public Debt() {
    }

    public Debt(double value){
        this.value = value;
    }

    public Debt(Integer amount, String description, String category) {
        this.value = amount;
        this.name = description;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
