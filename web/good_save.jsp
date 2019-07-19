<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-16
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品添加</title>
    <script type="text/javascript">
        function home() {
            window.location.href = "goodQueryServlet";
        }
    </script>
    <style type="text/css">
        input{
            text-align: center;
        }
        *{
            font-family: SimSun;
            color: gold;
            background-color: #336666;
        }
    </style>
</head>
<body>
<div style="width: 400px;margin: 0 auto">
<form action="goodInsertServlet" method="post" enctype="multipart/form-data">
    <table border="1" style="width: 400px;height: 50px;margin:0 auto;text-align: center">
        <caption style="font-weight: bold;font-size: 40px">商品添加</caption>
        <tr>
            <th>商品名字</th>
            <td><input type="text" name="goodsInfo_name" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品图片</th>
            <td><input type="file" name="goodsInfo_pic"/>
            </td>
        </tr>
        <tr>
            <th>商品价格</th>
            <td><input type="text" name="goodsInfo_price" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品简介</th>
            <td><input type="text" name="goodsInfo_description" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品库存</th>
            <td><input type="text" name="goods_stock" style="border: none"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="确认"/>&nbsp;
                <input type="button" value="清空全部" onclick="reset()"/>
            </td>
        </tr>
    </table>
    <input type="button" value="返回主菜单" onclick="home()"/>
</form>
</div>
</body>
</html>
