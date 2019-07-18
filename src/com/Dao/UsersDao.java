package com.Dao;

import com.entity.Users;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
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
    public void closeAll(Connection conn,Statement pra, ResultSet rs){
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
    public int addUsers(Users users){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = this.getConnection();
            String sql = " INSERT INTO users (username,password,sex,hobbys,phone,email,addrs,flag) VALUES (?,?,?,?,?,?,?,?)";
            List<Object> paramList = new ArrayList<>();
            paramList.add(users.getUsername());
            paramList.add(users.getPassword());
            paramList.add(users.getSex());
            paramList.add(users.getHobby());
            paramList.add(users.getPhone());
            paramList.add(users.getEmail());
            paramList.add(users.getAddress());
            paramList.add("激活");
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
    @Test
    public List<Users> queryUserByUsernameAndPassword(Users users){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        List<Users> list = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = " select * from users where username = ? and password = ? ";
            pra = conn.prepareStatement(sql);
            pra.setString(1,users.getUsername());
            pra.setString(2,users.getPassword());
            rs = pra.executeQuery();
            if (rs.next()){
                users = new Users();
                users.setId(rs.getInt("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setSex(rs.getString("sex"));
                users.setHobby(rs.getString("hobbys"));
                users.setPhone(rs.getString("phone"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("addrs"));
                users.setFlag(rs.getString("flag"));
                list.add(users);
            }
            System.out.println();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,pra,rs);
        }
        return list;
    }
}
