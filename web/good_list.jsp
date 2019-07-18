<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-16
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
    <script type="text/javascript">
        function updataGood(id) {
            window.location.href = "goodLoadDataServlet?id="+id+"&way="+1;
        }
        function checkGood (id){
            window.location.href = "goodLoadDataServlet?id="+id+"&way="+2;
        }
        function deleteGood(id) {
            window.location.href = "goodDeleteServlet?id="+id;
        }
        function addUser() {
            window.location.href = "good_save.jsp";
        }
        function backspace() {
            window.location.href = "login.jsp";
        }
    </script>
</head>
<body>
<div style="width: 900px;height: 200px;margin:0 auto">
<table border="1" style="width: 900px;height: 50px;margin:0 auto;text-align: center">
    <caption style="font-weight: bold;font-size: 20px">商品列表</caption>
    <tr>
        <th>序号</th>
        <th>商品名字</th>
        <th>商品图片</th>
        <th>商品价格</th>
        <th>商品简介</th>
        <th>商品库存</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="goods" varStatus="s">
        <tr>
            <td>${goods.id}</td>
            <td>${goods.goodsInfo_name}</td>
            <td  width="150px" height="150px">
                <img src="/upload/${goods.goodsInfo_pic}" width="150px" height="150px"/>
            </td>
            <td>${goods.goodsInfo_price}元</td>
            <td>${goods.goodsInfo_description}</td>
            <td>${goods.goods_stock}</td>
            <td style="text-align: center">
                <input type="button" value="修改" onclick="updataGood('${goods.id}')" name="update"><br/>
                <input type="button" value="删除" onclick="deleteGood('${goods.id}')"><br/>
                <a href="#" name="detail" onclick="checkGood('${goods.id}')" )>商品详情</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7" style="text-align: center">
            <input type="button" value="添加" onclick="addUser()">
        </td>
    </tr>
</table>
<input type="button" value="退出登录" onclick="backspace()">
</div>
</body>
</html>
