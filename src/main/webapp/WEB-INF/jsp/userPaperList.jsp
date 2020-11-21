<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-12
  Time: 10:13
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
                    ${sessionScope.loginUser.u_userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">密码修改</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/exitLogin">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有课程</a>
                    <dl class="layui-nav-child">
                        <c:forEach var="course" items="${sessionScope.courseList}">
                            <c:if test="${course.co_id eq thisCourse.co_id}">
                                <dd class="layui-this"><a href="/toPaperListUser?co_id=${course.co_id}">${course.c_name}</a></dd>
                            </c:if>
                            <c:if test="${course.co_id ne thisCourse.co_id}">
                                <dd><a href="/toPaperListUser?co_id=${course.co_id}">${course.c_name}</a></dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">班级管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <span class="layui-col-md6" style="font-size: 24px; margin-left: 7%;">${thisCourse.c_name}试卷列表</span>
                <span class="layui-col-md2 layui-input-inline" style="margin-left: 8%;"><input type="text" name="title" placeholder="请输入标题" autocomplete="off" class="layui-input" style="float: right;"></span>
                <span class="layui-col-md1"><button type="button" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button></span>
            </div>
            <div>
                <c:if test="${paperList.size() eq 0}">
                    <div class="layui-col-md10" style="left: 7%; margin-top: 15px;">
                        <div class="layui-card">
                            <div class="layui-card-header">没有该课程的试卷</div>
                            <div class="layui-card-body" style="font-size: 25px">老师还未发布任何试卷</div>
                        </div>
                    </div>
                </c:if>
                <c:forEach var="paper" items="${paperList}">
                        <div class="layui-col-md10" style="left: 7%; margin-top: 15px;">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span style="font-size: 18px;">${paper.p_title}</span>

                                    <c:if test="${paper.resultList.get(0).re_id ne null}">
                                        <span style="float: right; color: blue; font-size: 13px;">已答卷</span>
                                    </c:if>
                                    <c:if test="${paper.resultList.get(0).re_id eq null}">
                                        <span style="float: right; color: red; font-size: 13px;">未答卷</span>
                                    </c:if>
                                    <span style="margin-right: 10%; float: right;">考试时间：${paper.startTime}---${paper.endTime}</span>
                                </div>
                                <div class="layui-card-body">
                                    <span>试卷说明：${paper.p_desc}</span>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-btn-container">
                                        <button type="button" onclick="toWritePaper(${paper.p_id})" class="layui-btn layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>答题</button>
                                        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm" ><i class="layui-icon layui-icon-list"></i>成绩</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </c:forEach>

            </div>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;

    });
    function toWritePaper(p_id) {
        window.location.href = "/toWritePaper?p_id="+p_id;

    }
</script>
</body>
</html>