<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-03
  Time: 11:09
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
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">考试管理</a>
                    <dl class="layui-nav-child">
                        <c:forEach var="course" items="${sessionScope.courseList}">
                            <c:if test="${course.co_id eq thisCourse.co_id}">
                                <dd class="layui-this"><a href="/toPaperList?co_id=${course.co_id}">${course.co_name}</a></dd>
                            </c:if>
                            <c:if test="${course.co_id ne thisCourse.co_id}">
                                <dd><a href="/toPaperList?co_id=${course.co_id}">${course.co_name}</a></dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </li>
<%--                <li class="layui-nav-item"><a href="">班级管理</a></li>--%>
<%--                <li class="layui-nav-item"><a href="">用户管理</a></li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <span class="layui-col-md4" style="font-size: 24px; margin-left: 7%;">${thisCourse.co_name}试卷列表</span>
                <span class="layui-col-md2">
                    <button type="button" class="layui-btn layui-btn-normal" onclick="createPaper(${thisCourse.co_id})"><i class="layui-icon layui-icon-add-circle"></i>创建试卷</button>
                </span>
                <span class="layui-col-md2 layui-input-inline" style="margin-left: 8%;">
                    <input type="text" name="title" id="title" value="${sessionScope.searchTitle}" placeholder="请输入标题" autocomplete="off" class="layui-input" style="float: right;">
                </span>
                <span class="layui-col-md1"><button type="button" onclick="selectPaper(${thisCourse.co_id})" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button></span>
            </div>
            <div>
                <c:if test="${paperList.size() eq 0}">
                    <div class="layui-col-md10" style="left: 7%; margin-top: 15px;">
                        <div class="layui-card">
                            <div class="layui-card-header">可以点击上方按钮创建试卷</div>
                            <div class="layui-card-body" style="font-size: 25px">没有该课程的试卷</div>
                        </div>
                    </div>
                </c:if>
                <c:forEach var="paper" items="${paperList}">
                    <div class="layui-col-md10" style="left: 7%; margin-top: 15px;">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                <span style="font-size: 18px;">${paper.p_title}</span>
                                <c:if test="${paper.isRelease ne 1}">
                                    <span style="float: right; color: red; font-size: 13px;">未发布</span>
                                </c:if>
                                <c:if test="${paper.isRelease eq 1}">
                                    <span style="float: right; color: blue; font-size: 13px;">已发布</span>
                                </c:if>

                                <span style="float: right; margin-right: 10%;">答卷：${paper.resultCount}</span>
                            </div>
                            <c:if test="${paper.isRelease ne 1}">
                                <div class="layui-card-body">

                                        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" style="border: none;" onclick="toPaperSetting(${paper.p_id})"><i class="layui-icon layui-icon-play"></i>发布考试</button>

                                    <div class="layui-btn-container" style="float: right;">
                                        <button type="button" class="layui-btn layui-btn-disabled layui-btn-sm" >答卷情况</button>
                                        <button type="button" onclick="toEditPaper(${paper.p_id})" class="layui-btn layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                        <button type="button" onclick="deletePaper(${paper.p_id},${paper.co_id})" class="layui-btn layui-btn-danger layui-btn-sm"><i class="layui-icon layui-icon-delete"></i>删除</button>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${paper.isRelease eq 1}">
                                <div class="layui-card-body">
                                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" onclick="toStopPaper(${paper.p_id})" style="border: none;" ><i class="layui-icon layui-icon-pause"></i>停止发布</button>
                                    <div class="layui-btn-container" style="float: right;">
                                        <button type="button" onclick="toAnalysisPaper(${paper.p_id})" class="layui-btn layui-btn-normal layui-btn-sm" >答卷情况</button>
                                        <button type="button" class="layui-btn layui-btn-disabled layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                        <button type="button" class="layui-btn layui-btn-disabled layui-btn-sm"><i class="layui-icon layui-icon-delete"></i>删除</button>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 在线考试系统
    </div>
</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script src="/exam/js/public.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;

    });

    function selectPaper(co_id) {
        var title=$('#title').val();
        window.location.href="/selectPaper?title="+title+"&co_id="+co_id;
    }

    function createPaper(co_id){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.prompt({
                title:'请输入试卷名称',
                formType:0,
            },function(value,index){
                window.location.href = "/newPaper?t_title="+value+"&co_id="+co_id;
                layer.close(index);
            });
        });
    }

    function toEditPaper(p_id) {
        window.location.href = "/toEditPaper?p_id="+p_id;
    }
    function toAnalysisPaper(p_id) {
        window.location.href = "/toAnalysis?p_id="+p_id;
    }
    function toPaperSetting(p_id) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                scrollbar:false,
                resize:false,
                skin: 'layui-layer-rim',
                area: ['550px', '350px'],
                content: '/toPaperSetting?p_id='+p_id
            });
        });
    }
    function toStopPaper(p_id) {
        layui.use('layer', function() {
            var layer = layui.layer;
            layer.msg('你确定要停止试卷发布吗？', {
                time: 0 //不自动关闭
                , btn: ['确定', '取消']
                , yes: function (index) {
                    window.location.href = "/toStopPaper?p_id=" + p_id;
                    layer.close(index);
                }
            });
        });
    }
    function deletePaper(p_id,co_id) {
        layui.use('layer', function() {
            var layer = layui.layer;
            layer.msg('你确定要删除该试卷吗？', {
                time: 0 //不自动关闭
                , btn: ['确定', '取消']
                , yes: function (index) {
                    window.location.href = "/deletePaper?p_id="+p_id+"&co_id="+co_id;
                    layer.close(index);
                }
            });
        });

    }
</script>
</body>
</html>