package nationalmerchantsassociation.mynetworth.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jbrannen on 11/16/17.
 */

public class MonthConversionUtil {

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

    public static String monthIntTOString(int month){
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

    public static int monthStringToInt(String month){
        switch (month){
            case "January":
                return 0;
            case "February":
                return 1;
            case "March":
                return 2;
            case "April":
                return 3;
            case "May":
                return 4;
            case "June":
                return 5;
            case "July":
                return 6;
            case "August":
                return 7;
            case "September":
                return 8;
            case "October":
                return 9;
            case "November":
                return 10;
            case "December":
                return 11;
        }
        return -1;
    }
}
