package org.moonwave.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1Util
 * http://www.sha1-online.com/sha1-java/
 * 
 */
public class SHAUtil {

    /**
     * @param args
     * @throws NoSuchAlgorithmException 
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(sha1("test string to sha1"));
        System.out.println("=== Hope1 ===");
        System.out.println(sha1("Hope1"));
        System.out.println("=== aff ===");
        System.out.println(sha1("aff"));
        System.out.println("=== Qaz! ===");
        System.out.println(sha1("Qaz!"));

        System.out.println("=== aff - sha-2 ===");
        System.out.println(sha2("aff"));
        System.out.println("=== Qaz! - sha-2 ===");
        System.out.println(sha2("Qaz!"));

    }

    /**
     * SHA-1 ⇒ 40 digit hexadecimal number
     *
     */
    public static String sha1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http://stackoverflow.com/questions/6840206/sha2-password-hashing-in-java
     * SHA-2 ⇒ 64 digit hexadecimal number
     * 
     * @param password
     * @return
     */
    public static String sha2(String password) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            String salt = "random_salt_77";
            String passWithSalt = password + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] result = mDigest.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< result.length ;i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
        return sha2(password);
    }

}
