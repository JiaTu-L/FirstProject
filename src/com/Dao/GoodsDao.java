package com.Dao;

import com.entity.GoodsInfo;
import com.entity.Users;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/project?characterEncoding=utf8&useSSL=false&serverTimezone=CST&rewriteBatchedStatements=true";
    private static final String username = "root";
    private static final String password = "king100718";
    @Test
    public Connection getConnection(){
        try {
            Class.forName(DRIVER_CLASS);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
    @Test
    public void closeAll(Connection conn, Statement pra, ResultSet rs){
        try {
            if(rs!=null)
                rs.close();
            if(pra!=null)
                pra.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public List<GoodsInfo> queryGoodsInfoByGoods(GoodsInfo goodsInfo){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        List<GoodsInfo> Goodlist = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        GoodsInfo goods;
        try {
            StringBuffer sf = new StringBuffer();
            conn = this.getConnection();
            sf.append(" select * from goodsInfo Where 1=1 ");
            if(goodsInfo!=null){
                if(goodsInfo.getId()!=0){
                    sf.append(" and id = ? ");
                    list.add(goodsInfo.getId());
                }
            }
            pra = conn.prepareStatement(sf.toString());
            for (int i = 0; i <list.size() ; i++) {
                pra.setObject(i+1,list.get(i));
            }
            rs = pra.executeQuery();
            while (rs.next()){
                goods = new GoodsInfo();
                goods.setId(rs.getInt("id"));
                goods.setGoodsInfo_name(rs.getString("goodsInfo_name"));
                goods.setGoodsInfo_pic(rs.getString("goodsInfo_pic"));
                goods.setGoodsInfo_price(rs.getDouble("goodsInfo_price"));
                goods.setGoodsInfo_description(rs.getString("goodsInfo_description"));
                goods.setGoods_stock(rs.getInt("goods_stock"));
                Goodlist.add(goods);
            }
            System.out.println();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,pra,rs);
        }
        return Goodlist;
    }
    @Test
    public int updataGoodsInfo(GoodsInfo goods){
        StringBuffer sf = new StringBuffer();
        List<Object> paramList = new ArrayList<>();
        sf.append(" update goodsinfo set ");
        sf.append(" goodsInfo_pic = ? ");
        paramList.add(goods.getGoodsInfo_pic());
        if(goods.getGoodsInfo_name()!=null){
            sf.append(" ,goodsInfo_name = ? ");
            paramList.add(goods.getGoodsInfo_name());
        }
        if(goods.getGoodsInfo_price()>=0){
            sf.append(" ,goodsInfo_price = ? ");
            paramList.add(goods.getGoodsInfo_price());
        }
        if(goods.getGoodsInfo_description()!=null){
            sf.append(" ,goodsInfo_description = ? ");
            paramList.add(goods.getGoodsInfo_description());
        }
        if(goods.getGoods_stock()>=0){
            sf.append(" ,goods_stock = ? ");
            paramList.add(goods.getGoods_stock());
        }
        sf.append(" where id = ? ");
        paramList.add(goods.getId());
        return this.Goods(sf.toString(),paramList);
    }
    @Test
    public int deleteGoodsInfo(GoodsInfo goods){
        String sql = " delete from goodsinfo where id = ? ";
        List<Object> paramList = new ArrayList<>();
        paramList.add(goods.getId());
        return this.Goods(sql,paramList);
    }
    @Test
    public int addGoodsInfo(GoodsInfo goods){
        String sql = " INSERT INTO goodsinfo (goodsInfo_name,goodsInfo_pic,goodsInfo_price,goodsInfo_description,goods_stock) VALUES (?,?,?,?,?) ";
        List<Object> paramList = new ArrayList<>();
        paramList.add(goods.getGoodsInfo_name());
        paramList.add(goods.getGoodsInfo_pic());
        paramList.add(goods.getGoodsInfo_price());
        paramList.add(goods.getGoodsInfo_description());
        paramList.add(goods.getGoods_stock());
        return this.Goods(sql,paramList);
    }
    @Test
    public int Goods(String sql,List<Object> paramList){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = this.getConnection();
            pra = conn.prepareStatement(sql);
            if(paramList!=null){
                for (int i = 0; i <paramList.size() ; i++) {
                    pra.setObject(i+1,paramList.get(i));
                }
            }
            rows = pra.executeUpdate();
            if(rows>0){
                return rows;
            }else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,pra,rs);
        }return rows;
    }
}
