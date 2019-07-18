package com.Servlet;

import com.Dao.GoodsDao;
import com.entity.GoodsInfo;
import com.entity.Users;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class GoodUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        int rows = 0;
        try {
//            GoodsInfo goodsInfo = this.getGoodsInfoByNormalForm(req);
            GoodsInfo goodsInfo = this.uploadFile(req,resp);
            GoodsDao goodsDao = new GoodsDao();
            rows = goodsDao.updataGoodsInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0){
            out.print("<script type = 'text/javascript'>alert('修改成功');location.href = 'goodQueryServlet'</script>");
        }else {
            out.print("<script type = 'text/javascript'>alert('修改失败："+errMsg+"');history.back()</script>");
        }
    }

    private GoodsInfo getGoodsInfoByNormalForm(HttpServletRequest req) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(Integer.parseInt(req.getParameter("id")));
        goodsInfo.setGoodsInfo_name(req.getParameter("goodsInfo_name"));
        goodsInfo.setGoodsInfo_pic(req.getParameter("goodsInfo_pic"));
        goodsInfo.setGoodsInfo_price(Double.parseDouble(req.getParameter("goodsInfo_price")));
        goodsInfo.setGoodsInfo_description(req.getParameter("goodsInfo_description"));
        goodsInfo.setGoods_stock(Integer.parseInt(req.getParameter("goods_stock")));
        System.out.println();
        return goodsInfo;
    }

    public GoodsInfo uploadFile(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        GoodsInfo goodsInfo = new GoodsInfo();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> fileItemsList = upload.parseRequest(req);
            if(fileItemsList!=null && fileItemsList.size()>0){
                for (FileItem fileItem : fileItemsList) {
                    if(fileItem.isFormField()){
                        String fieldName = fileItem.getFieldName();
                        if("goodsInfo_name".equals(fieldName)){
                            if(fileItem.getString("utf-8") == null || "".equals(fileItem.getString("utf-8"))){
                                throw new RuntimeException("商品名称不能为空");
                            }else {
                                goodsInfo.setGoodsInfo_name(fileItem.getString("utf-8"));
                            }
                        }else if("goodsInfo_price".equals(fieldName)){
                            if(fileItem.getString() == null || "".equals(fileItem.getString())){
                                throw new RuntimeException("商品价格不能为空");
                            }
                            if(Double.parseDouble(fileItem.getString())<0){
                                throw new RuntimeException("商品价格不能为负数");
                            }
                            goodsInfo.setGoodsInfo_price(Double.parseDouble(fileItem.getString()));
                        }else if("goodsInfo_description".equals(fieldName)){
                            if(fileItem.getString("utf-8") == null || "".equals(fileItem.getString("utf-8"))){
                                throw new RuntimeException("商品描述不能为空");
                            }else {
                                goodsInfo.setGoodsInfo_description(fileItem.getString("utf-8"));
                            }
                        }else if("goods_stock".equals(fieldName)) {
                            if (fileItem.getString() == null || "".equals(fileItem.getString())) {
                                throw new RuntimeException("商品库存不能为空");
                            }
                            if(Integer.parseInt(fileItem.getString())<0){
                                throw new RuntimeException("商品库存不能为负数");
                            }
                            goodsInfo.setGoods_stock(Integer.parseInt(fileItem.getString()));
                        }else if("goodsInfo_pic".equals(fieldName)){
                            goodsInfo.setGoodsInfo_pic(fileItem.getString("utf-8"));
                        }
                        else if("id".equals(fieldName)){
                            goodsInfo.setId(Integer.parseInt(fileItem.getString()));
                        }
                    }else {
                        String fileName = fileItem.getName();
                        String parentPath = req.getServletContext().getRealPath("/upload");
                        File parentFile = new File(parentPath);
                        if(!parentFile.exists()) parentFile.mkdirs();
                        File newFile = new File(parentFile,fileName);
                        InputStream is = fileItem.getInputStream();
                        OutputStream os = new FileOutputStream(newFile);
                        IOUtils.copy(is,os);
                        os.close();
                        is.close();
                        goodsInfo.setGoodsInfo_pic(fileName);
                    }
                }
            }
        }else {

        }
        return goodsInfo;
    }
}
