package com.movers.eazymovers.common.util;

import com.movers.eazymovers.dal.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;

public class ThreadLocalContextHolder {
    private static final ThreadLocal<HashMap<String,Object>> sessionAttributeHolder = new ThreadLocal<>();

    public static void setCurrentUser(User user){
        if(RequestContextHolder.getRequestAttributes() == null){
            HashMap<String,Object> map = sessionAttributeHolder.get();
            if(map == null){
                map = new HashMap<>();
                sessionAttributeHolder.set(map);
            }
            map.put("currentUser",user);
        }else {
            RequestContextHolder.getRequestAttributes()
                    .setAttribute("currentUser",user, RequestAttributes.SCOPE_REQUEST);
        }
    }

    public static User getCurrentUser(){
        if(RequestContextHolder.getRequestAttributes() == null){
            HashMap<String,Object> map = sessionAttributeHolder.get();
            if(map == null){
                map = new HashMap<>();
                sessionAttributeHolder.set(map);
            }
            return (User) map.get("currentUser");
        }else {
            User user = (User) RequestContextHolder.getRequestAttributes()
                    .getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
            return user;
        }
    }
}
