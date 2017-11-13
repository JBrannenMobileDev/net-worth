package nationalmerchantsassociation.mynetworth.utils;

/**
 * Created by jbrannen on 5/19/17.
 */

public interface BaseCallback<T> {
    void onResponse(T object);
    void onFailure(Exception e);
}
