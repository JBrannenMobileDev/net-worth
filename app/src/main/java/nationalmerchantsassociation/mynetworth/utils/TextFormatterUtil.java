package nationalmerchantsassociation.mynetworth.utils;

import java.text.DecimalFormat;

/**
 * Created by jbrannen on 11/17/17.
 */

public class TextFormatterUtil {
    public static DecimalFormat getCurrencyFormatter(){
        return new DecimalFormat("###,###,###");
    }
}
