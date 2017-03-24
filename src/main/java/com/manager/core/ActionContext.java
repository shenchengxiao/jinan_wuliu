package com.manager.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActionContext {
    private static final ThreadLocal<ActionContext> actionContextThreadLocal
            = new ThreadLocal<ActionContext>(){};

    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuthUser user;

    public ServletContext getContext() {
        return context;
    }

    public HttpServletRequest getHttpRequest() {
        return request;
    }

    public HttpServletResponse getHttpResponse() {
        return response;
    }

    public HttpSession getHttpSession() {
        return request.getSession();
    }

    public AuthUser currentUser(){
        if(this.user==null){
            this.user = AuthUser.getCurrentUser();
        }
        return this.user;
    }


    public static ActionContext getActionContext() {
        return actionContextThreadLocal.get();
    }

    public static void setActionContext(ServletContext context,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionContext ctx = new ActionContext();
        ctx.context = context;
        ctx.request = request;
        ctx.response = response;
        actionContextThreadLocal.set(ctx);
    }

    static void removeActionContext() {
        actionContextThreadLocal.remove();
    }
}
