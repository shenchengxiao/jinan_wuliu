package com.manager.utils;

import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolsUtil {
	
	/**
	 * 本地时间转化成UTC时间
	 * @return
	 */
	public static long toUTC(Date date){
		
		Calendar cal = Calendar.getInstance();
		
		if(null!=date){
			cal.setTime(date);
		}
    	
    	int zoneOffset = cal.get(Calendar.ZONE_OFFSET);//取得时间偏移量
    	int dstOffset = cal.get(Calendar.DST_OFFSET);//取得夏令时差
    	
    	cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset)); //UTC时间
    	
    	return cal.getTimeInMillis();
	}
	
	/**
	 * 判断到期时间是否大于今天
	 * @param stopTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean isOverToday(String stopTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return (sdf.parse(stopTime).getTime() > new Date().getTime());
	}
	
	public static Date parseDate(String time,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		if(null != time || !"".equals(time)){
			return sdf.parse(time);
		}
		
		return null;
		
	}
	
	public static String parseString(Date time,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		if(null != time){
			return sdf.format(time);
		}
		
		return null;
	}
	
	public static String getTheMonthDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();   
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());
	}
	
	public static String getLastMonthDay(){ 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        return format.format(ca.getTime());
	}
	
	//明年今天
	public static String getNextYearToDay(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
    
      Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.YEAR,1);//加一个年       
      str=sdf.format(lastDate.getTime());    
      return str;      
            
    }
	
	//昨天
	public static String getYestDay(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
    
      Calendar lastDate = Calendar.getInstance();    
      lastDate.set(Calendar.HOUR_OF_DAY, -1);     
      str=sdf.format(lastDate.getTime());    
      return str;      
            
    }
	
	//今天
	public static String getToDay(){    
        String str = "";    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
        str=sdf.format(new Date());    
        return str;      
            
    }
	
	public static void main(String[] args){
		
	}
	
	/**
	 * byte——>String
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
	
	/**
     *   转换byte数组为Image
     *   @param   filename 为要生成新的文件名
     *   @return   string
     */
    public static String ByteToImage(byte[] b,int userId,String filename){
        String path = "D:"+File.separator+"authphoto"+File.separator
                +userId;
        try {
            File binaryFile = new File(path);
            if(!binaryFile.exists()){
                binaryFile.mkdir();
            }

            String imgPath = path+File.separator+filename+".jpg";

            File f = new File(imgPath);

            /*FileOutputStream fileOutStream = new FileOutputStream(f);
            if(b!=null){
            	for(int i=0;i<b.length;i++)
                {
                    fileOutStream.write(b[i]);
                }
                fileOutStream.flush();
                fileOutStream.close();
            }*/
            
            FileImageOutputStream imageOutput = new FileImageOutputStream(f);
            imageOutput.write(b, 0, b.length);
            imageOutput.close();
            return imgPath;
        } /*catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        catch(Exception ex) {
            ex.printStackTrace();
         }
        return null;
    }
    
    /**  
     * 解码  
     * @param str  
     * @return string  
     */    
    public static byte[] decode(String str){    
    byte[] bt = null;    
    try {    
        BASE64Decoder decoder = new BASE64Decoder();
        if(str != null && !"".equals(str.trim())){
        	bt = decoder.decodeBuffer(str);
        }
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
        return bt;    
    }
    
    /**
     * 判断是否是Integer类型
     * @author daichangfu
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if(str!=null&&!"".equals(str.trim())){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            Long number = 0l;
            if(isNum.matches()){
                number=Long.parseLong(str);
            }else{
                return false;
            }
            if(number>2147483647){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

}
