package com.Servlet;

import com.Dao.UsersDao;
import com.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String sex = req.getParameter("sex");
        String hobbys = req.getParameter("hobbys");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String addrs = req.getParameter("addrs");
        String errMsg = "";
        int rows = 0;
        try {
            if(username == null || "".equals(username) || password1 == null || "".equals(password1)){
                throw new RuntimeException("用户名或密码不能为空");
            }
            if(!password1.equals(password2)){
                throw new RuntimeException("密码和确认密码必须相等");
            }
            UsersDao usersDao = new UsersDao();
            Users users = new Users(username,password1,sex,hobbys,phone,email,addrs);
            rows = usersDao.addUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0){
            out.print("<script type='text/javascript'>alert('注册成功');location.href = 'login.jsp';</script>");
        } else {
            out.print("<script type='text/javascript'>alert('注册失败:" + errMsg + "');history.back();</script>");
        }
    }
}
