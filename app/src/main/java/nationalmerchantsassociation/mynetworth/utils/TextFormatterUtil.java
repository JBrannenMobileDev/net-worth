package nationalmerchantsassociation.mynetworth.utils;

import java.text.DecimalFormat;

/**
 * Created by jbrannen on 11/17/17.
 */

public class TextFormatterUtil {
    public static DecimalFormat getCurrencyFormatter(){
        return new DecimalFormat("###,###,###");
    }

    public static String buildLineChartTitle(String range, int netChange, double mostRecentAssetValue){
        try {
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + "(" + netChange / mostRecentAssetValue * 100 + "%) Past " + range;
        }catch(ArithmeticException div_zero){
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + " Past " + range;
        }
    }
}
