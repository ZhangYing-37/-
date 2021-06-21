<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统登录 - 在线考试系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/exam/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/exam/layui/css/layui.css">
</head>

<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader"></header>
        <section class="loginCont">
            <form class="layui-form layui-form-pane loginForm">
                <h1 class="loginHeader2">在线考试系统</h1>
                <div class="inputbox">
                    <label for="user">账号：</label>
                    <input id="user" type="text" name="userCode" placeholder="请输入学号/工号" lay-verify="required" required/>
                </div>
                <div class="inputbox">
                    <label for="mima">密码：</label>
                    <input id="mima" type="password" name="password" placeholder="请输入密码" lay-verify="required" required/>
                </div>
                <div>
                    <input type="radio" name="role" value="1" title="管理员">
                    <input type="radio" name="role" value="2" title="教师">
                    <input type="radio" name="role" value="3" title="学生" checked>
                </div>

                <div class="layui-btn-container" style="margin-top:10px;">
                    <button class="layui-btn" style="margin-left: 70px" id="getSumbit" lay-submit="" lay-filter="formLogin">登录</button>
                    <button type="reset" style="margin-left: 30px" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </form>
        </section>
    </section>

</body>
<script src="${pageContext.request.contextPath}/exam/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/exam/js/jquery.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(formLogin)', function (data) {
            console.log(data.field);
            $.ajax({
                url:"${pageContext.request.contextPath}/checkLogin",
                type:"post",
                async:false,
                data: JSON.stringify(data.field),
                contentType:"application/json",
                dataType:"text",
                success:function (data1) {
                    if (data1==="ok"){
                        window.location.href = "${pageContext.request.contextPath}/loginGo?role="+data.field.role;
                    }else {
                        layui.layer.msg('用户名或者密码错误!');
                    }
                }
            })
            return false;
        });
    });
</script>
</html>