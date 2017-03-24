package com.manager.filter;


import com.manager.core.ActionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shencx on 2017/3/24.
 */
public class ActionContextBuildFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        //暂时先加着
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session  = httpServletRequest.getSession();

        //设置 ActionContext
        ActionContext.setActionContext(session.getServletContext(),
                (HttpServletRequest)request,
                (HttpServletResponse) response);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
