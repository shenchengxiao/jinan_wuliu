package com.manager.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * comment: MD5加密对象
 * Created by shencx on 2017/3/24.
 */
public class MD5Util {

    /**
     * 将字符串参数加密为 MD5
     *
     * @param encContent 待 MD5加密的内容
     * @return
     */
    public static String encryptMD5(String encContent) throws NoSuchAlgorithmException {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = encContent.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Throwable e) {
            throw e;
        }
    }
}
