package com.manager.common.tool;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtil {
	
	public String randomData(){
		Random random = new Random();
		
		int num = random.nextInt(899999)+100000;
		
		return num+"";
	}
	
    //验证手机号码  
	public static boolean phoneNumber(String number)  
    {  
        String rgx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";  
          
        return isCorrect(rgx, number);  
    }  
      
    //验证身份证号码  
    public static boolean idCardNumber(String number)  
    {  
        String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";  
          
        return isCorrect(rgx, number);  
    }  
      
    //正则验证  
    public static boolean isCorrect(String rgx, String res)  
    {  
        Pattern p = Pattern.compile(rgx);  
          
        Matcher m = p.matcher(res);  
          
        return m.matches();  
    }  
    
    public static void main(String [] args){

    }
}
