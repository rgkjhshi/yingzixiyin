package com.yingzixiyin.api.utils;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class StringUtils {

    public static String listToString(List list) {
        if (null == list) {
            return null;
        }
        StringBuilder builder = new StringBuilder("");
        for (Object o : list) {
            builder.append(o.toString());
        }
        return builder.toString();
    }
}
