package com.manager.core;


import com.manager.utils.MD5Util;

import java.security.NoSuchAlgorithmException;

/**
 * Created by shencx on 2017/3/24.
 */
public class PasswordEncrypt {

    //密码加密
    public static String encrypt(String username,String password){
        try {
            return MD5Util.encryptMD5(String.format("%s$$%s",username,password));
        }catch (NoSuchAlgorithmException e){
            return "";
        }
    }
}
