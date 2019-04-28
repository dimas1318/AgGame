package com.hackaton.agilegamificator;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
final public class StringUtils {

    private static final String CURRENCY = "GCoins";

    public static String formatBalance(int balance) {
        return balance + " " + CURRENCY;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }
}
