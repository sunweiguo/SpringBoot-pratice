package com.tygq.demo04.utils;

import java.security.MessageDigest;

public class DigestUtil {
    public static String MD5(String msg) {
        if (msg == null) {
            return null;
        } else {
            char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

            try {
                byte[] strTemp = msg.getBytes();
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char[] str = new char[j * 2];
                int k = 0;

                for(int i = 0; i < j; ++i) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 15];
                    str[k++] = hexDigits[byte0 & 15];
                }

                String dd = new String(str);
                return dd;
            } catch (Exception var10) {
                return null;
            }
        }
    }
}
