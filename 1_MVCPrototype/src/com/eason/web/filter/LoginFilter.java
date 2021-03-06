package com.eason.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String uri = req.getRequestURI();

        if(uri.contains("/login.jsp")  || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet")
                || uri.contains("/css/")|| uri.contains("/js/") || uri.contains("/fonts/")){
            filterChain.doFilter(req,servletResponse);
        }else{
            HttpSession session = req.getSession();
            Object user = session.getAttribute("user");
            if (user != null){
                filterChain.doFilter(req,servletResponse);
            }else {
                req.setAttribute("login_msg","Please Login before Accessing!");
                req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
            }
        }


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
