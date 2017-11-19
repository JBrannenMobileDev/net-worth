package nationalmerchantsassociation.mynetworth.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jbrannen on 11/16/17.
 */

public class YearListUtil {

    /**
     * Generates the year list for create_asset and create_debt views.
     * @return
     */
    public static List<Integer> generateYearList() {
        List<Integer> yearList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        yearList.add(currentYear);
        yearList.add(currentYear-1);

        return yearList;
    }
}
