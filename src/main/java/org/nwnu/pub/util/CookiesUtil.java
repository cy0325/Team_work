package org.nwnu.pub.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookiesUtil {

	 /**  
     * 添加cookie  
     * @param response  
     * @param name  
     * @param value  
     */  
    public static void addCookie(HttpServletResponse response,String name,String value,int time){  
        Cookie cookie = new Cookie(name.trim(), value.trim());  
        cookie.setMaxAge(time);// 设置为30min  
        cookie.setPath("/");  
        System.out.println("已添加===============");  
        response.addCookie(cookie);  
    } 
    
    /**  
     * 修改cookie  
     * @param request  
     * @param response  
     * @param name  
     * @param value  
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。  
     */  
    public void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){  
        Cookie[] cookies = request.getCookies();  
        if (null==cookies) {  
            System.out.println("没有cookie==============");  
        } else {  
            for(Cookie cookie : cookies){  
                if(cookie.getName().equals(name)){  
                    System.out.println("原值为:"+cookie.getValue());  
                    cookie.setValue(value);  
                    cookie.setPath("/");  
                    cookie.setMaxAge(60 * 60 * 7);// 设置为30min  
                    System.out.println("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());  
                    response.addCookie(cookie);  
                    break;  
                }  
            }  
        }  
    }  
    
    /**  
     * 删除cookie  
     * @param request  
     * @param response  
     * @param name  
     */  
    public void delCookie(HttpServletRequest request,HttpServletResponse response,String name){  
        Cookie[] cookies = request.getCookies();  
        if (null==cookies) {  
            System.out.println("没有cookie==============");  
        } else {  
            for(Cookie cookie : cookies){  
                if(cookie.getName().equals(name)){  
                    cookie.setValue(null);  
                    cookie.setMaxAge(0);// 立即销毁cookie  
                    cookie.setPath("/");  
                    System.out.println("被删除的cookie名字为:"+cookie.getName());  
                    response.addCookie(cookie);  
                    break;  
                }  
            }  
        }  
    }  
    
    /**  
     * 根据名字获取cookie  
     * @param request  
     * @param name cookie名字  
     * @return  
     */  
    public static Cookie getCookieByName(HttpServletRequest request,String name){  
        Map<String,Cookie> cookieMap = ReadCookieMap(request);  
        if(cookieMap.containsKey(name)){  
            Cookie cookie = (Cookie)cookieMap.get(name);  
            return cookie;  
        }else{  
            return null;  
        }     
    }
    
    
    /**  
     * 将cookie封装到Map里面  
     * @param request  
     * @return  
     */  
    private  static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){    
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();  
        Cookie[] cookies = request.getCookies();  
        if(null!=cookies){  
            for(Cookie cookie : cookies){  
                cookieMap.put(cookie.getName(), cookie);  
            }  
        }  
        return cookieMap;  
    }  
}
