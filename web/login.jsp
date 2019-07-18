<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-7-11
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <script type="text/javascript">
        function register() {
            window.location.href = "register.jsp";
        }
    </script>
</head>
<body>
<form action="loginServlet" method="post">
    <table border="1" style="width:300px;height:50px;margin: 0 auto">
        <caption style="font-weight: bold;font-size: 20px">用户登录</caption>
        <tr>
            <th>用户名</th>
            <td><input type="text" name="username" style="border: none"/></td>
        </tr>
        <tr>
            <th>密码</th>
            <td><input type="password" name="password" style="border: none"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="登录"/>&nbsp;
                <input type="button" value="注册" onclick="register()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
