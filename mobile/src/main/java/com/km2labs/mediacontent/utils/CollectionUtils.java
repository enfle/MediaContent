package com.km2labs.mediacontent.utils;

import java.util.Collection;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection items) {
        return items == null || items.size() <= 0;
    }
}
