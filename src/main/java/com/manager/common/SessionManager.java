package com.manager.common;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
	
	private static ConcurrentHashMap<Integer, HttpSession> mapOnline = new ConcurrentHashMap<Integer, HttpSession>();

    /**
     * 将用户添加到在线列表
     * @param userCode
     * @param sessionId
     */
    public static synchronized void addUser(int userCode, HttpSession session) {
        if (mapOnline.containsKey(userCode)){
        	HttpSession session2 = mapOnline.get(userCode);
        	
        	if(!session.getId().equals(session2.getId())){
        		mapOnline.remove(userCode);
        	}
        	
        }
            
        mapOnline.put(userCode, session);
    }
    
    /**
     * 是否为合法用户
     * @param userCode
     * @param sessionId
     * @return
     */
    public static boolean isValidUser(int userCode, HttpSession session) {
        if (!mapOnline.containsKey(userCode))
            return false;
        
        if (!mapOnline.get(userCode).equals(session))
            return false;

        return true;
    }

}
