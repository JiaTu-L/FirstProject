<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-15
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册界面</title>
    <script type="text/javascript">
        function login() {
            window.location.href = "login.jsp";
        }
    </script>
</head>
<body>
<form action="registerServlet" method="post">
    <table border="1" style="width: 300px;height: 50px;margin:0 auto">
        <caption style="font-weight: bold;font-size: 20px">用户注册</caption>
        <tr>
            <th>用户名</th>
            <td><input type="text" name="username" style="border: none"/></td>
        </tr>
        <tr>
            <th>密码</th>
            <td><input type="password" name="password1" style="border: none"/></td>
        </tr>
        <tr>
            <th>确认密码</th>
            <td><input type="password" name="password2" style="border: none"/></td>
        </tr>
        <tr>
            <th>性别</th>
            <td><input type="text" name="sex" style="border: none"/></td>
        </tr>
        <tr>
            <th>爱好</th>
            <td><input type="text" name="hobbys" style="border: none"/></td>
        </tr>
        <tr>
            <th>手机号码</th>
            <td><input type="text" name="phone" style="border: none"/></td>
        </tr>
        <tr>
            <th>电子邮箱</th>
            <td><input type="text" name="email" style="border: none"/></td>
        </tr>
        <tr>
            <th>地址</th>
            <td><input type="text" name="addrs" style="border: none"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="确认"/>&nbsp;
                <input type="button" value="返回" onclick="login()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
