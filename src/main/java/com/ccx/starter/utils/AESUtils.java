package com.ccx.starter.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Xcc
 * @Title: 加密工具类
 * @Package com.ccx.starter.utils
 * @Description: 使用 Java 自带的 Cipher 来实现对称加密，使用 AES 算法
 * @date 2021/12/23 16:24
 */
public class AESUtils {

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * @return javax.crypto.Cipher
     * @Description 获取 cipher
     * @Date 2021/12/23
     * @Param [key, model]
     **/
    private static Cipher getCipher(byte[] key, int model) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        final Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }


    /**
     * @return java.lang.String
     * @Description AES加密
     * @Date 2021/12/23
     * @Param [data, key]
     **/
    public static String encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }


    /**
     * @return byte[]
     * @Description AES解密
     * @Date 2021/12/23
     * @Param [data, key]
     **/
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        final Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(Base64.getDecoder().decode(data));
    }


}
