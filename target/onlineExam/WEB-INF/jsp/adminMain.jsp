<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-20
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>在线考试系统</title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-size: 24px;">在线考试系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${sessionScope.loginTeacher.t_userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a style="cursor: pointer;" onclick="toTeacherMessage()">基本资料</a></dd>
                    <dd><a style="cursor: pointer;" onclick="toTeacherPassword()">密码修改</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/exitLogin">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item"><a href="/toClassesList">班级管理</a></li>
                <li class="layui-nav-item"><a href="/toTeacherList">教师管理</a></li>
                <li class="layui-nav-item"><a href="/toUserList">用户管理</a></li>
                <li class="layui-nav-item"><a href="/toCourseList">课程管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray">
        <!-- 内容主体区域 -->
        <div style="padding: 15px; ">
            <span style="font-size: 30px; margin-left: 30%;">
                ${sessionScope.loginTeacher.t_userName}，欢迎使用在线考试系统
            </span>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 在线考试系统
    </div>
</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/public.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
