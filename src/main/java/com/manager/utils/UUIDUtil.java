package com.manager.utils;

import java.util.UUID;

/**
 * comment: 随机码
 *
 * @author: shenchengxiao
 * @date: 16/9/13 下午4:10
 */
public class UUIDUtil {
    private static final String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    /**
     * 随机生成8位字母数字码
     *
     * @return
     */
    public static final String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    public static String generateUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateUuid36() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(generateUuid32());
        System.out.println(generateUuid36());
    }
}