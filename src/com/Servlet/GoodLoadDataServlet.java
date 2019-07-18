package com.Servlet;

import com.Dao.GoodsDao;
import com.entity.GoodsInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GoodLoadDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        try {
            String goodsInfo_id = req.getParameter("id");
            if(goodsInfo_id == null || "".equals(goodsInfo_id)){
               throw new RuntimeException("学号不能为空");
            }
            int id = Integer.parseInt(goodsInfo_id);
            GoodsDao goodsDao = new GoodsDao();
            List<GoodsInfo> list = goodsDao.queryGoodsInfoByGoods(new GoodsInfo(id));
            if(list == null || list.size()<1){
                throw new RuntimeException("学号找不到");
            }
            req.setAttribute("goods",list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        if("".equals(errMsg)){
            String way = req.getParameter("way");
            if("1".equals(way)){
                req.getRequestDispatcher("good_update.jsp").forward(req,resp);
            }else if("2".equals(way)){
                req.getRequestDispatcher("good_detail.jsp").forward(req,resp);
            }

        }

        PrintWriter out = resp.getWriter();
        out.print("<script type='text/javascript'>alert('数据加载失败:"+errMsg+"');history.back();</script>");
    }
}
