<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-16
  Time: 19:12
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
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">考试管理</a>
                    <dl class="layui-nav-child">
                        <c:forEach var="course" items="${sessionScope.courseList}">
                            <dd><a href="/toPaperList?co_id=${course.co_id}">${course.c_name}</a></dd>
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
            <table id="scoreAnalysis" lay-filter="test"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="analysis">结果分析</button>
                </div>
            </script>

            <script type="text/html" id="barDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="look">批改</button>
                </div>
            </script>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="/exam/layui/layui.js"></script>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#scoreAnalysis'
            ,toolbar: '#toolbarDemo'
            ,defaultToolbar: ['filter', 'exports', 'print']
            ,url: '/getAnalysis' //数据接口
            ,page: true //开启分页
            ,limits : [10,20,30]
            ,limit : 10
            ,cols: [[ //表头
                {field: 'userCode', title: '学号', width:'12%', sort: true, fixed: 'left'}
                ,{field: 'userName', title: '姓名', width:'9%'}
                ,{field: 'title', title: '试卷名称', align:'center', width:'16%'}
                ,{field: 'dX_score', title: '单选', width:'8%', sort:true}
                ,{field: 'dX1_score', title: '多选', width: '8%', sort:true}
                ,{field: 'pD_score', title: '判断', width: '8%', sort: true}
                ,{field: 'tK_score', title: '填空', width: '8%', sort: true}
                ,{field: 'jD_score', title: '简答', width: '8%', sort:true}
                ,{field: 'totalScore', title: '总分', width: '8%', sort: true}
                ,{field: 'isCorrect', title: '状态', width: '8%', sort: true, templet:'#correctTpl'}
                ,{fixed: 'right', width:'7%', title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(test)', function(obj){
            var layEvent = obj.event;
            if (layEvent === 'look'){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.load();
                    setTimeout(function(){
                        layer.closeAll('loading');
                    }, 500);
                });
                console.log(obj.data);
                window.location.href="/correctPaper?u_id="+obj.data.u_id;
            }
        });
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'analysis':
                    layer.msg('结果分析');
                    break;
            };
        });
    });
</script>
<script type="text/html" id="correctTpl">
    {{#  if(d.isCorrect === '未批改'){ }}
    <span style="color: red;">{{ d.isCorrect }}</span>
    {{#  } else { }}
    {{ d.isCorrect }}
    {{#  } }}
</script>
</body>
</html>