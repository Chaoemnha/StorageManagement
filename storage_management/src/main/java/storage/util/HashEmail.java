package storage.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEmail {
	public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                & 0xFF) | 0x100).substring(1,3));        
        }
        return sb.toString();
    }
 
    public static String sha256Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return hex(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            // Consider logging this exception or rethrowing as a RuntimeException
        } catch (UnsupportedEncodingException e) {
            // Consider logging this exception or rethrowing as a RuntimeException
        }
        return null;
    }
}
