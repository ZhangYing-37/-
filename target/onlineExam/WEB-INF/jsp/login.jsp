<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统登录 - 在线考试系统</title>
    <link rel="stylesheet" href="/exam/css/style.css"/>
    <script src="/exam/js/jquery.js"></script>
</head>

<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>在线考试系统</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="/checkLogin" method="post">
                <div class="inputbox">
                    <label for="user">用户编号：</label>
                    <input id="user" type="text" name="userCode" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label for="mima">密码：</label>
                    <input id="mima" type="password" name="password" placeholder="请输入密码" required/>
                </div>
                <input type="radio" name="role" value="1">管理员
                <input type="radio" name="role" value="2">教师
                <input type="radio" name="role" value="3" checked>学生
                <div class="subBtn">
                    <input type="submit" value="登录" />
                    <input type="reset" value="重置"/>
                </div>
            </form>
            <div>${tips}</div>
        </section>
    </section>

</body>
</html>