package com.seejoke.net.utils;

import java.util.Map;

/**
 * @author yangzhongying
 * @date 2020/5/11 13:52
 * @see com.seejoke.net.utils.StringUtils
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean mapIsNotBlank(Map s) {
        if (s == null || s.size() <= 0) {
            return false;
        }
        return true;
    }
}
