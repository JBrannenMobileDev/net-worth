package nationalmerchantsassociation.mynetworth.data_layer.models;

import java.util.Calendar;

import io.realm.RealmObject;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;

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

    public long getDateLong(){
        Calendar current = Calendar.getInstance();
        current.set(year, MonthConversionUtil.monthStringToInt(month), 1);
        return current.getTimeInMillis();
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
