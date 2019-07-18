package com.Servlet;

import com.Dao.GoodsDao;
import com.entity.GoodsInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GoodDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsInfo_id = req.getParameter("id");
        int rows = 0;
        String errMsg = "";
        try {
            if(goodsInfo_id==null){
                throw new RuntimeException("序号不能为空");
            }
            int id = Integer.parseInt(goodsInfo_id);
            GoodsDao goodsDao = new GoodsDao();
            GoodsInfo goodsInfo = new GoodsInfo(id);
            rows = goodsDao.deleteGoodsInfo(goodsInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0) {
            out.print("<script type='text/javascript'>alert('删除成功');location.href='goodQueryServlet'</script>");
        }else {
            out.print("<script type='text/javascript'>alert('删除失败："+errMsg+"');location.href='goodQueryServlet'</script>");
        }
    }
}
