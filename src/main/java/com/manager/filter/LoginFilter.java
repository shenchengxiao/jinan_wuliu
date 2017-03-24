package com.manager.filter;

import com.manager.common.SessionManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
	 
     public void init(FilterConfig filterConfig) throws ServletException {
 
     }
 
     public void doFilter(ServletRequest request, ServletResponse response,
             FilterChain chain) throws IOException, ServletException {
         // 获得在下面代码中要用的request,response,session对象
         HttpServletRequest servletRequest = (HttpServletRequest) request;
         HttpServletResponse servletResponse = (HttpServletResponse) response;
         HttpSession session = servletRequest.getSession();
 
         // 获得用户请求的URI
         String path = servletRequest.getRequestURI();
         //System.out.println(path);
         
         // 从session里取管理员id信息
         Integer id =  (Integer) session.getAttribute("id");
         
         String url = servletRequest.getContextPath();
         
         /*创建类Constants.java，里面写的是无需过滤的页面
         for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {
 
             if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                 chain.doFilter(servletRequest, servletResponse);
                 return;
             }
     }*/
         
         
         // 登陆页面和静态页面无需过滤
         if(path.indexOf("/login") > -1 || path.indexOf("/static") > -1 ) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
 
         
         if(id != null && SessionManager.isValidUser(id, session)){
        	 
        	 chain.doFilter(request, response);
        	 
         }else{
        	 java.io.PrintWriter out = response.getWriter();
       	  	 out.println("<html>");  
       	  	 out.println("<script>");
        	 if(path.indexOf("/mobile") > -1){
        		 out.println("window.open ('"+url+"/mobile/login.jsp','_top')");
        	 }else{
        		 out.println("window.open ('"+url+"/page/login.do','_top')");
        	 }
       	  	 out.println("</script>");  
       	  	 out.println("</html>");
         }
 
     }
 
     public void destroy() {
        
     }
 
 }
