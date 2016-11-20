package edu.dev.util;

import org.springframework.util.StringUtils;

/**
 * Created by tengzhao on 11/19/16.
 */
public class CommonUtils {

    public static long countOfString(String origin, String target) {
        return StringUtils.countOccurrencesOf(origin.toLowerCase(), target.toLowerCase());
    }
}
