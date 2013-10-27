package com.worldofzaar.util;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 26.10.13
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public class PasswordGenerator {
    public static String generatePassword(String userEmail) {
        return HashConverter.md5(userEmail);
    }
}
