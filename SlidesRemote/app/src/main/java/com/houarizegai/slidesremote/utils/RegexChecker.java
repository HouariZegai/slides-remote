package com.houarizegai.slidesremote.utils;

import java.util.regex.Pattern;

public class RegexChecker {
    private static final String IP_PATTERN = "^(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))"
            + "[.](([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))"
            + "[.](([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))"
            + "[.](([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))";

    private static boolean isValid(String input, String regex) {
        if(input == null) {
            return false;
        }

        return Pattern.matches(regex, input);
    }

    public static boolean isIP(String input) {
        return isValid(input, RegexChecker.IP_PATTERN);
    }
}
