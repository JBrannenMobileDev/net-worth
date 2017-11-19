package nationalmerchantsassociation.mynetworth.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;

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

    public static List<Integer> mapHistoricalNetWorth(RealmResults<Asset> assets, RealmResults<Debt> debts, int numOfMonths) {
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);

        return generateNetWorthList(assets, debts, numOfMonths, currentMonth, currentYear);
    }

    private static List<Integer> generateNetWorthList(RealmResults<Asset> assets, RealmResults<Debt> debts,
                                                      int numOfMonths, int currentMonth, int currentYear) {
        List<Integer> resultNetWorths = new ArrayList<>();
        List<Asset> assetsFor1Month;
        List<Debt> debtsFor1Month;
        for(int i = 0; i < numOfMonths; i++){
            List<Asset> list = new ArrayList<>();
            for (Asset asset : assets) {
                if (asset.getValueItemByDate(CustomeDateFormatter.
                        createDate(MonthConversionUtil.
                                monthIntTOString(currentMonth), currentYear)) != null) {
                    list.add(asset);
                }
            }
            assetsFor1Month = list;
            List<Debt> result = new ArrayList<>();
            for (Debt debt : debts) {
                if (debt.getValueItemByDate(CustomeDateFormatter.
                        createDate(MonthConversionUtil.
                                monthIntTOString(currentMonth), currentYear)) != null) {
                    result.add(debt);
                }
            }
            debtsFor1Month = result;

            resultNetWorths.add(calculateNetWorth(assetsFor1Month, debtsFor1Month));

            //reduce month by one
            if(currentMonth > 1){
                currentMonth--;
            }else{
                currentMonth = 12;
                currentYear--;
            }
        }
        Collections.reverse(resultNetWorths);
        return resultNetWorths;
    }

    private static Integer calculateNetWorth(List<Asset> assets, List<Debt> debts){
        double assetValueSum = assets == null ? 0:assets.stream().mapToDouble(asset -> asset.getCurrentValueItem().getValue()).sum();
        double debtValueSum = debts == null ? 0:debts.stream().mapToDouble(debt -> debt.getCurrentValueItem().getValue()).sum();
        return (int)(assetValueSum - debtValueSum);
    }
}
