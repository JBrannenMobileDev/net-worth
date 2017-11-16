package nationalmerchantsassociation.mynetworth.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jbrannen on 11/16/17.
 */

public class MonthListUtil {

    public static List<String> generateMonthList(){
        List<String> months = new ArrayList<>();
        Calendar current = Calendar.getInstance();
        String month = monthIntTOString(current.get(Calendar.MONTH));
        months.add(month);
        while(months.size() < 12){
            months.add(getNextMonth(month));
            month = getNextMonth(month);
        }
        return months;
    }

    private static String getNextMonth(String month) {
        switch (month){
            case "January":
                return "February";
            case "February":
                return "March";
            case "March":
                return "April";
            case "April":
                return "May";
            case "May":
                return "June";
            case "June":
                return "July";
            case "July":
                return "August";
            case "August":
                return "September";
            case "September":
                return "October";
            case "October":
                return "November";
            case "November":
                return "December";
            case "December":
                return "January";
        }
        return null;
    }

    private static String monthIntTOString(int month){
        switch (month){
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
        }
        return "January";
    }
}
