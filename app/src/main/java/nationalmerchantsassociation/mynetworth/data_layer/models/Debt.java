package nationalmerchantsassociation.mynetworth.data_layer.models;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import nationalmerchantsassociation.mynetworth.utils.CustomeDateFormatter;
import nationalmerchantsassociation.mynetworth.utils.MonthConversionUtil;

/**
 * Created by jbrannen on 11/7/17.
 */

public class Debt extends RealmObject{
    @PrimaryKey
    private String name;
    private String date;
    private String category;
    private RealmList<ValueItem> debtValues;

    public Debt() {}

    public Debt(Integer value, String description, String category, String month, int year) {
        this.name = description;
        this.category = category;
        this.debtValues = new RealmList<>();
        this.debtValues.add(new ValueItem(value, month, year));
        this.date = CustomeDateFormatter.createDate(month, year);
    }

    public void addValueItem(ValueItem item){
        debtValues.add(item);
    }

    public RealmList<ValueItem> getDebtValues() {
        return debtValues;
    }

    public void setDebtValues(RealmList<ValueItem> debtValues) {
        this.debtValues = debtValues;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ValueItem getCurrentValueItem() {
        int maxYear = debtValues.stream().mapToInt(ValueItem::getYear).max().getAsInt();
        List<ValueItem> currentYearList = debtValues.stream().filter(value -> value.getYear() == maxYear).collect(Collectors.toList());
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

    public ValueItem getValueItemByDate(String date){
        return debtValues.stream().filter(debt -> debt.getDate().equals(date)).findFirst().get();
    }

    private String getDate(){
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
