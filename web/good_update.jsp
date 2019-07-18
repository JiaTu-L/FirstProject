<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-16
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改</title>
    <script type="text/javascript">
        function home() {
            window.location.href = "goodQueryServlet";
        }
        function rechangepic(_obj) {
            var td = _obj.parentElement;
            var content = '<input type="file" name="goodsInfo_pic"/>';
            content+='<input type="button" value="取消选择" onclick="returnpic(this)">';
            td.innerHTML=content;
        }
        function returnpic(_obj){
            var td = _obj.parentElement;
            var content = '<img src="/upload/${goods.goodsInfo_pic}" width="250px" height="250px" name="goodsInfo_pic"/><br/>';
            content+='<input type="hidden" name="goodsInfo_pic" value="${goods.goodsInfo_pic}"/>'
            content+='<input type="button" value="重新选择" onclick="rechangepic(this)">';
            td.innerHTML=content;
        }
    </script>
    <style type="text/css">
        input{
            text-align: center;
        }
    </style>
</head>
<body>
<div style="width: 400px;margin: 0 auto">
<form action="goodUpdateServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${goods.id}"/>
    <table border="1" style="width: 400px;height: 50px;margin:0 auto;text-align: center">
        <caption style="font-weight: bold;font-size: 20px">商品修改</caption>
        <tr>
            <th>商品名字</th>
            <td><input type="text" name="goodsInfo_name" value="${goods.goodsInfo_name}" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品图片</th>
            <td width="250px" height="280px">
                <img src="/upload/${goods.goodsInfo_pic}" width="250px" height="250px" /><br/>
                <input type="hidden" name="goodsInfo_pic" value="${goods.goodsInfo_pic}"/>
                <input type="button" value="重新选择" onclick="rechangepic(this)">
            </td>
        </tr>
        <tr>
            <th>商品价格</th>
            <td><input type="text" name="goodsInfo_price" value="${goods.goodsInfo_price}" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品简介</th>
            <td><input type="text" name="goodsInfo_description" value="${goods.goodsInfo_description}" style="border: none"/></td>
        </tr>
        <tr>
            <th>商品库存</th>
            <td><input type="text" name="goods_stock" value="${goods.goods_stock}" style="border: none"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="确定"/>
            </td>
        </tr>
    </table>
    <input type="button" value="返回主菜单" onclick="home()"/>
</form>
</div>
</body>
</html>
