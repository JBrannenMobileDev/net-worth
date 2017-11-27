package nationalmerchantsassociation.mynetworth.data_layer.models;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;

/**
 * Created by jbrannen on 11/7/17.
 */

public class Asset extends RealmObject{
    @PrimaryKey
    private String name;
    private String date;
    private String category;
    private RealmList<ValueItem> assetValues;

    public Asset() {}

    public Asset(double value, String description, String categroy, String month, int year) {
        this.name = description;
        this.category = categroy;
        this.assetValues = new RealmList<>();
        this.assetValues.add(new ValueItem(value, month, year));
        this.date = CustomDateFormatter.createDate(month, year);
    }

    public void addValueItem(ValueItem item){
        assetValues.add(item);
    }

    public List<ValueItem> getAssetValues() {
        return assetValues;
    }

    public RealmList<ValueItem> getAssetValuesRealm() {
        return assetValues;
    }

    public void setAssetValues(RealmList<ValueItem> assetValues) {
        this.assetValues = assetValues;
    }

    public ValueItem getValueItemByDate(String date){
        return assetValues.stream().filter(asset -> asset.getDate().equals(date)).findFirst().orElse(null);
    }

    public ValueItem getCurrentValueItem() {
        int maxYear = assetValues.stream().mapToInt(ValueItem::getYear).max().getAsInt();
        List<ValueItem> currentYearList = assetValues.stream().filter(value -> value.getYear() == maxYear).collect(Collectors.toList());
        ValueItem nearestToCurrentValue;
        Calendar current = Calendar.getInstance();
        int currentMonth = current.get(Calendar.MONTH);
        nearestToCurrentValue = currentYearList.get(0);
        for(ValueItem value : currentYearList){
             int valueMonth = MonthConversionUtil.monthStringToInt(value.getMonth());
             if(valueMonth == currentMonth){
                 return value;
             }
             if(valueMonth > MonthConversionUtil.monthStringToInt(nearestToCurrentValue.getMonth())){
                 nearestToCurrentValue = value;
             }
        }
        return nearestToCurrentValue;
    }

    public double getCurrentValue() {
        int maxYear = assetValues.stream().mapToInt(ValueItem::getYear).max().getAsInt();
        List<ValueItem> currentYearList = assetValues.stream().filter(value -> value.getYear() == maxYear).collect(Collectors.toList());
        ValueItem nearestToCurrentValue;
        Calendar current = Calendar.getInstance();
        int currentMonth = current.get(Calendar.MONTH);
        nearestToCurrentValue = currentYearList.get(0);
        for(ValueItem value : currentYearList){
            int valueMonth = MonthConversionUtil.monthStringToInt(value.getMonth());
            if(valueMonth == currentMonth){
                return value.getValue();
            }
            if(valueMonth > MonthConversionUtil.monthStringToInt(nearestToCurrentValue.getMonth())){
                nearestToCurrentValue = value;
            }
        }
        return nearestToCurrentValue.getValue();
    }

    private String getDate(){
        return date;
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
}
