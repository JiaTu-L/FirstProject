package com.filter;

import com.entity.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {
    private List<String> urlFilterList = Arrays.asList(
            "/login.jsp",
            "/loginServlet",
            "/register.jsp",
            "/registerServlet",
            ".jpg",
            ".png",
            ".bmp"
    );
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String uri = req.getRequestURI();
//        boolean flag = false;
//        if(urlFilterList != null && urlFilterList.size()>0){
//            for (String uriStr : urlFilterList) {
//                if(uriStr.indexOf("/")==0){
//                    if(uri.startsWith(uriStr)){
//                        flag = true;
//                        break;
//                    }
//                }else{
//                    if(uri.endsWith(uriStr)){
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//        }
//        if(flag){
//            filterChain.doFilter(servletRequest, servletResponse);
//        }else{
//            HttpSession session = req.getSession();
//            Users loginUser = (Users) session.getAttribute("userInfo");
//            if(loginUser!=null){
//                filterChain.doFilter(servletRequest, servletResponse);
//            }else{
//                PrintWriter out = resp.getWriter();
//                out.println("<script type='text/javascript'>alert('请先登录');location.href='login.jsp';</script>");
//                req.setAttribute("msg", "请先登录");
//                req.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
//            }
//        }
        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('请先登录');location.href='login.jsp';</script>");
        } else {
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
