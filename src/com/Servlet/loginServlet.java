package com.Servlet;

import com.Dao.UsersDao;
import com.entity.Users;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String errMsg = "";
        PrintWriter out = resp.getWriter();
        UsersDao usersDao = new UsersDao();
        List<Users> list;
        int rows = 0;
        try {
            if(username == null || "".equals(username) || password == null || "".equals(password)){
                throw new RuntimeException("账号或密码不能为空");
            }
            list = usersDao.queryUserByUsernameAndPassword(new Users(username,password));
            if(list.size()>0) {
                req.getSession().setAttribute("user",new Users(username,password));
                out.print("<script type='text/javascript'>alert('登录成功');location.href='goodQueryServlet'</script>");
            }else {
                throw new RuntimeException("账号密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
            out.print("<script type='text/javascript'>alert('登录失败：" + errMsg + "');history.back()</script>");
        }
    }
}
