package com.example.study.dubboExample;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/11 10:14
 **/
public class DESUtils {
    private static Key key;
    private static String KEY_STR = "%-!JzoXZ";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    public DESUtils() {
    }

    public static String getEncryptString(String str) {
        try {
            byte[] bytes = str.getBytes(CHARSETNAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(1, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return Base64.encodeBase64String(doFinal);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String getDecryptString(String str) {
        try {
            byte[] bytes = Base64.decodeBase64(str);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(2, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
