package com.worldofzaar.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 10.06.13
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class HashConverter {

    public static String md5(String s) {
        String hash = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            hash = new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }

    public static String md5File(byte[] fileBytes) {
        String hash = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(fileBytes, 0, fileBytes.length);
            hash = new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }
}
