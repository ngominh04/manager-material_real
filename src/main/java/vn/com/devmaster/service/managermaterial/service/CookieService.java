package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    // đọc cookie từ request
    public Cookie get(String name){
        Cookie[] cookies =request.getCookies();
        if(cookies != null){
            for (Cookie cookie: cookies){
                if(cookie.getName().equalsIgnoreCase(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    // đọc giá trị cookie từ request
    public String getValue(String name,String defaultValue){
        Cookie cookie = get(name);
        if (cookie != null){
            return cookie.getValue();
        }
        return defaultValue;
    }

    // tạo và gửi cookie về client
    public Cookie add(String name,String value,int hourse){
        Cookie cookie =new Cookie(name,value);
        cookie.setMaxAge(hourse*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }

    // xóa cookie ra khỏi client
    public  void remove(String name){
        add(name,"",0);
    }



}
