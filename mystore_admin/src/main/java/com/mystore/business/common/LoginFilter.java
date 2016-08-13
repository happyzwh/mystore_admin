package com.mystore.business.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class LoginFilter implements Filter{
	
	private final static String FILTER_URL_EXCLUDE = "excludeUrl";
    private static String[] excludeURLs;
	private final static String FILTER_URL_LOGIN = "loginUrl";
    private static String loginUrl;
    private final static String FILTER_ISFILETER = "isFilter";
    private static boolean isFilter = false;
    

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;
		
		 if(!isFilter){
			  chain.doFilter(req,res); 
	   		  return;
	   	 }
		
		 String url = req.getRequestURI();
	     boolean flag = false;
	     if (excludeURLs != null && excludeURLs.length > 0) {
	            for(String excludeURL : excludeURLs) {
	                if (url.contains(excludeURL.trim())) {
	                    flag = true;
	                    break;
	                }
	            }
	     }
		
        if(flag){
        	chain.doFilter(req,res); 
        	return;
        }
        
	    HttpSession session = req.getSession(true); 

	    Object o =  session.getAttribute(Constans.KEY_SESSION); 
	    
	    if (o == null){ 
	    	res.sendRedirect(req.getContextPath() +"/"+loginUrl); 
	    }else{ 
	    	chain.doFilter(req,res); 
	    }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 String urlStr = filterConfig.getInitParameter(FILTER_URL_EXCLUDE);
		 excludeURLs = StringUtils.split(urlStr, ",");
		 loginUrl = filterConfig.getInitParameter(FILTER_URL_LOGIN);
		 isFilter = Boolean.valueOf(filterConfig.getInitParameter(FILTER_ISFILETER));
	}

}
