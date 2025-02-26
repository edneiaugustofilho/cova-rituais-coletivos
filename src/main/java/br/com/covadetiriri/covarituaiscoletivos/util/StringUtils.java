package br.com.covadetiriri.covarituaiscoletivos.util;

public class StringUtils {

    public static boolean isBlankOrNull(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNotBlankOrNull(String value) {
        return !isBlankOrNull(value);
    }

}
