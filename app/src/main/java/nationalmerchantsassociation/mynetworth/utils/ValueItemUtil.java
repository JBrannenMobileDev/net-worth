package nationalmerchantsassociation.mynetworth.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;

/**
 * Created by jbrannen on 11/24/17.
 */

public class ValueItemUtil {
    public static List<ValueItem> sortByDate(List<ValueItem> listToSort){
        Comparator<ValueItem> mostRecentFirst = Comparator.comparing(item -> item.getDateLong());
        List<ValueItem> values = listToSort.stream()
                .sorted(mostRecentFirst)
                .collect(Collectors.toList());
        Collections.reverse(values);
        return values;
    }
}
