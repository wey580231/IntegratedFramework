package com.rengu.util;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class SuperAction extends ActionSupport implements ServletContextAware, ServletRequestAware, ServletResponseAware {
    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected HttpSession httpSession;
    protected ServletContext servletContext;

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.httpSession = httpServletRequest.getSession();
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
