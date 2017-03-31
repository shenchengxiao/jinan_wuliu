package com.manager.interceptor;

import com.manager.annotations.Authentication;
import com.manager.core.ActionContext;
import com.manager.core.ApiResponse;
import com.manager.core.AuthUser;
import com.manager.exception.YCIllegalAccessException;
import com.manager.utils.JsonMapper;
import com.manager.utils.UserRoleEnum;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    public static final String UTF8 = "UTF-8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            verify((HandlerMethod)handler);
            return true;
        }catch (YCIllegalAccessException e){
            response.setHeader("Content-type","application/json;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            response.setCharacterEncoding(UTF8);//设置Response的编码方式为UTF-8
            String json = JsonMapper.buildNonDefaultBinder().toJson(ApiResponse.Create(400,e.getMessage()));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }
        return false;
    }
    private void verify(HandlerMethod handler) throws YCIllegalAccessException {
        ActionContext context = ActionContext.getActionContext();
        AuthUser user = context.currentUser();

        if(!user.isAuthorized()) throw new YCIllegalAccessException("用户未登录"); //未登录用户

        Authentication auth = handler.getMethodAnnotation(Authentication.class);
        if(auth==null) auth = AnnotationUtils.findAnnotation(handler.getBeanType(),Authentication.class);
        if(auth==null) return;

        //deney
        for (UserRoleEnum role:auth.deny()) {
            if((role.getValue() & user.getUserRole()) > 0)
                throw new YCIllegalAccessException("禁止访问");
        }

        //allow
        for (UserRoleEnum role:auth.allow()) {
            if((role.getValue() & user.getUserRole()) > 0) return;
        }

        throw new YCIllegalAccessException("禁止访问");
    }

}
