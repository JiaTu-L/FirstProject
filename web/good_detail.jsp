<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-18
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>商品详情</title>
<script type="text/javascript">
    function home() {
        window.location.href = "goodQueryServlet";
    }
</script>
    <style type="text/css">
        *{
            font-family: SimSun;
            color: gold;
            background-color: #336666;
            text-align: center;
        }
    </style>
</head>
<body>
<div style="width: 300px;margin:0 auto">
<input type="hidden" name="id" value="${goods.id}"/>
<table border="1" style="width: 300px;height: 50px;margin:0 auto">
    <caption style="font-weight: bold;font-size: 40px">商品详情</caption>
    <tr>
        <th>商品名字</th>
        <td>${goods.goodsInfo_name}</td>
    </tr>
    <tr>
        <th>商品图片</th>
        <td width="250px" height="250px">
            <img src="/upload/${goods.goodsInfo_pic}" width="250px" height="250px" />
        </td>
    </tr>
    <tr>
        <th>商品价格</th>
        <td>${goods.goodsInfo_price}</td>
    </tr>
    <tr>
        <th>商品简介</th>
        <td>${goods.goodsInfo_description}</td>
    </tr>
    <tr>
        <th>商品库存</th>
        <td>${goods.goods_stock}</td>
    </tr>
</table>
<input type="button" value="返回主菜单" onclick="home()"/>
</div>
</body>
</html>
