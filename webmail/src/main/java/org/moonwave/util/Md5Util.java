package org.moonwave.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5Util
 * 
 */
public class Md5Util {

    protected static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1,3));
        }
        return sb.toString();
    }

    /**
     * Gets md5 sum for a given message string.
     * 
     * MD5 ⇒ 32 digit hexadecimal number
     * 
     * @param message message string.
     * @return
     */
    public static String getMd5Sum (String message) { 
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            return hex (md.digest(message.getBytes("CP1252"))); 
        } catch (NoSuchAlgorithmException e) { 
        } catch (UnsupportedEncodingException e) { 
        }
        return null;
    }
    
    /**
     * Returns encrypted password for a given clear text password
     * 
     * @param password clear text password.
     * @return encrypted password.
     */
    public static String encryptPassword (String password) {
        return Md5Util.getMd5Sum(password);
    }

    public static void main(String[] vargs) {
        String str = encryptPassword("hope");
        System.out.println("hope: '" + str + "'");
        str = encryptPassword("hope1^");
        System.out.println("hope1^: '" + str + "'");
    }
}
