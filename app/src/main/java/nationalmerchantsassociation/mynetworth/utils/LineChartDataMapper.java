package nationalmerchantsassociation.mynetworth.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details.AssetDetailsModel;
import nationalmerchantsassociation.mynetworth.view_layer.activities.main.MainActivityModel;

/**
 * Created by jbrannen on 11/18/17.
 */

public class LineChartDataMapper {
    public static final int MONTHS_3 = 3;
    public static final int MONTHS_6 = 6;
    public static final int MONTHS_12 = 12;
    public static final int YEARS_3 = 36;
    public static final int YEARS_5 = 60;
    public static final int YEARS_10 = 120;
    public static final int ALL = 999;

    public static MainActivityModel mapHistoricalNetWorth(RealmResults<Asset> assets, RealmResults<Debt> debts, int numOfMonths) {
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);

        MainActivityModel result = new MainActivityModel();
        result.setNetWorths(generateNetWorthList(assets, debts, numOfMonths, currentMonth, currentYear, result));

        return result;
    }

    public static AssetDetailsModel mapAssetDetails(List<ValueItem> assetValues, int numOfMonths){
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);

        AssetDetailsModel result = new AssetDetailsModel();
        generateAssetDetailsData(result, assetValues, numOfMonths, currentMonth, currentYear);
        return result;
    }

    private static void generateAssetDetailsData(AssetDetailsModel result, List<ValueItem> assetValues, int numOfMonths, int currentMonth, int currentYear) {
        List<Integer> assetValue = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<ValueItem> valueItems = new ArrayList<>();

        for(int i = 0; i < numOfMonths; i++){
            ValueItem found = null;
            for (ValueItem item : assetValues) {
                if (item.getDate().equals(CustomDateFormatter.createDate(MonthConversionUtil.monthIntTOString(currentMonth), currentYear))) {
                    valueItems.add(item);
                    found = item;
                    break;
                }else{
                    valueItems.add(new ValueItem());
                }
            }
            if(found == null){
                assetValue.add(0);
            }else {
                assetValue.add((int) found.getValue());
            }

            dates.add(CustomDateFormatter.
                    createDate(MonthConversionUtil.
                            monthIntTOString(currentMonth), currentYear));

            //reduce month by one
            if(currentMonth > 1){
                currentMonth--;
            }else{
                currentMonth = 12;
                currentYear--;
            }
        }
        Collections.reverse(assetValue);
        Collections.reverse(dates);
        Collections.reverse(valueItems);
        result.setValueItemList(valueItems);
        result.setAssetValues(assetValue);
        result.setDates(dates);
    }

    private static List<Integer> generateNetWorthList(RealmResults<Asset> assets, RealmResults<Debt> debts,
                                                          int numOfMonths, int currentMonth, int currentYear, MainActivityModel result) {
        List<Integer> resultNetWorths = new ArrayList<>();
        List<Integer> resultAssets = new ArrayList<>();
        List<Integer> resultDebts = new ArrayList<>();
        List<String> resultDates = new ArrayList<>();
        List<ValueItem> assetsFor1Month;
        List<ValueItem> debtsFor1Month;
        for(int i = 0; i < numOfMonths; i++){
            List<ValueItem> assetsList = new ArrayList<>();
            for (Asset asset : assets) {
                if (asset.getValueItemByDate(CustomDateFormatter.
                        createDate(MonthConversionUtil.
                                monthIntTOString(currentMonth), currentYear)) != null) {
                    assetsList.add(asset.getValueItemByDate(CustomDateFormatter.
                            createDate(MonthConversionUtil.
                                    monthIntTOString(currentMonth), currentYear)));
                }
            }
            assetsFor1Month = assetsList;

            List<ValueItem> debtsList = new ArrayList<>();
            for (Debt debt : debts) {
                if (debt.getValueItemByDate(CustomDateFormatter.
                        createDate(MonthConversionUtil.
                                monthIntTOString(currentMonth), currentYear)) != null) {
                    debtsList.add(debt.getValueItemByDate(CustomDateFormatter.
                            createDate(MonthConversionUtil.
                                    monthIntTOString(currentMonth), currentYear)));
                }
            }
            debtsFor1Month = debtsList;

            resultDates.add(CustomDateFormatter.
                    createDate(MonthConversionUtil.
                            monthIntTOString(currentMonth), currentYear));
            resultAssets.add(assetsFor1Month.stream().mapToInt(valueItem -> (int)valueItem.getValue()).sum());
            resultDebts.add(debtsFor1Month.stream().mapToInt(valueItem -> (int)valueItem.getValue()).sum());
            resultNetWorths.add(calculateNetWorth(assetsFor1Month, debtsFor1Month));

            //reduce month by one
            if(currentMonth > 1){
                currentMonth--;
            }else{
                currentMonth = 12;
                currentYear--;
            }
        }
        Collections.reverse(resultDates);
        Collections.reverse(resultAssets);
        Collections.reverse(resultDebts);
        Collections.reverse(resultNetWorths);
        result.setAssets(resultAssets);
        result.setDebts(resultDebts);
        result.setDates(resultDates);
        return resultNetWorths;
    }

    private static Integer calculateNetWorth(List<ValueItem> assets, List<ValueItem> debts){
        double assetValueSum = assets == null ? 0:assets.stream().mapToDouble(asset -> asset.getValue()).sum();
        double debtValueSum = debts == null ? 0:debts.stream().mapToDouble(debt -> debt.getValue()).sum();
        return (int)(assetValueSum - debtValueSum);
    }
}
