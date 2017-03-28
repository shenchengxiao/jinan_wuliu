/**
 * 
 */
package com.manager.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作 Cookie 公共对象
 *
 */
public class CookieUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class.getName());
	public static final String  DOMAIN = "";
	
	public static void writeCookie(final HttpServletResponse response, String domain, String name, String value, int expireInterval) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
//		cookie.setDomain(domain);

        if(expireInterval>0) cookie.setMaxAge(expireInterval);
		response.addCookie(cookie);
	}

    public static void writeCookie(final HttpServletResponse response, String name, String value) {
        writeCookie(response, DOMAIN, name, value, 0);
    }

	public static void writeCookie(final HttpServletResponse response,
			String name, String value, int expireInterval) {
        writeCookie(response, DOMAIN, name, value, expireInterval);
	}

	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
            if(cookie != null && StringUtils.isNotBlank(cookie.getValue())) {
                return cookie.getValue();
            }
		} else {
            String cookieVal = request.getHeader("cookie");
            if(StringUtils.isNotBlank(cookieVal)) {
                String[] cookies = cookieVal.split(";");
                if(cookies != null) {
                    for(String cookie : cookies) {
                        String[] nameVal = cookie.split("=");
                        if(nameVal != null && nameVal.length == 2 && StringUtils.isNotBlank(nameVal[0]) && nameVal[0].trim().equals(name)) {
                            return StringUtils.defaultIfBlank(nameVal[1], StringUtils.EMPTY).trim();
                        }
                    }
                }
            }
		}
        return null;
    }

	public static void removeCookie(HttpServletResponse response, String name, String domain) {
        Cookie userCookie = new Cookie(name, null);
		userCookie.setPath("/");
        userCookie.setDomain(domain);
		userCookie.setMaxAge(0);
		response.addCookie(userCookie);
	}

    public static void removeCookie(HttpServletResponse response, String name) {
        removeCookie(response, name, DOMAIN);
    }

	public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
		return cookieMap;
	}

    public static String toString(Cookie cookie) {
        if(null == cookie) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getClass()).append(":{");
        sb.append("name:").append(cookie.getName()).append(",");
        sb.append("path:").append(cookie.getPath()).append(",");
        sb.append("value:").append(cookie.getValue()).append(",");
        sb.append("domain:").append(cookie.getDomain()).append(",");
        sb.append("comment:").append(cookie.getComment()).append(",");
        sb.append("maxAge:").append(cookie.getMaxAge()).append(",");
        sb.append("secure:").append(cookie.getSecure()).append(",");
        sb.append("version:").append(cookie.getVersion()).append(".}");
        return sb.toString();
    }


   /* public static String genarateUUID() {
        return AESClientUtil.encryptStr(DataDict.COOKIE_UUID_PREFIX + System.currentTimeMillis());
    }

    public static boolean isValidUUID(HttpServletRequest request) {
        String value = CookieUtils.getCookieByName(request, DataDict.COOKIE_UUID_PREFIX);
        if(StringUtils.isNotBlank(value)) {
            String descryptStr = AESClientUtil.decryptStr(value);
            return StringUtils.isNotBlank(descryptStr) && descryptStr.startsWith(DataDict.COOKIE_UUID_PREFIX);
        }
        return false;
    }*/
}
