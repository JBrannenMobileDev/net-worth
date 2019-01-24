package nationalmerchantsassociation.mynetworth.utils;

import java.text.DecimalFormat;

/**
 * Created by jbrannen on 11/17/17.
 */

public class TextFormatterUtil {
    public static DecimalFormat getCurrencyFormatter(){
        return new DecimalFormat("###,###,###");
    }

    public static String buildLineChartTitle(String range, int netChange, double mostRecentValue){
        try {
            if(mostRecentValue > 0) {
                return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + "(" + getPercentageFormat().format(netChange / mostRecentValue * 100) + "%) Past " + range;
            }else{
                return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + "(0%) Past " + range;
            }
        }catch(ArithmeticException div_zero){
            return "$" + TextFormatterUtil.getCurrencyFormatter().format(netChange) + " Past " + range;
        }
    }

    public static DecimalFormat getPercentageFormat(){
        return new DecimalFormat("###,###,###");
    }
}
