package nationalmerchantsassociation.mynetworth.data_layer.models;

import io.realm.RealmObject;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;

/**
 * Created by jbrannen on 11/17/17.
 */

public class ValueItem extends RealmObject{
    private String date;
    private double value;
    private String month;
    private int year;

    public ValueItem() {
        this.date = "";
        this.month = "";
    }

    public ValueItem(double value, String month, int year) {
        this.value = value;
        this.month = month;
        this.year = year;
        this.date = CustomDateFormatter.createDate(month, year);
    }

    public String getDate(){
        return date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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
}
