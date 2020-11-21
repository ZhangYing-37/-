<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-20
  Time: 15:24
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
                <li class="layui-nav-item"><a href="/toClassesList">班级管理</a></li>
                <li class="layui-nav-item"><a href="">教师管理</a></li>
                <li class="layui-nav-item"><a href="">用户管理</a></li>
                <li class="layui-nav-item"><a href="">课程管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <table id="classes" lay-filter="test"></table>

            <script type="text/html" id="toolbarHead">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="createClass">创建班级</button>
                </div>
            </script>

            <script type="text/html" id="barRight">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="edit">编辑</button>
                    <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</button>
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
            elem: '#classes'
            ,toolbar: '#toolbarHead'
            ,defaultToolbar: ['filter', 'exports', 'print']
            ,url: '/selectAllClasses' //数据接口
            ,page: true //开启分页
            ,limits : [10,20,30]
            ,limit : 10
            ,cols: [[ //表头
                {field: 'c_grade', title: '年级', sort: true}
                ,{field: 'c_code', title: '班级号', sort: true}
                ,{field: 'c_name', title: '班级名称'}
                ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barRight'}
            ]]
        });

        table.on('tool(test)', function(obj){
            var layEvent = obj.event;
            if (layEvent === 'edit'){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        type: 2,
                        scrollbar:false,
                        resize:false,
                        skin: 'layui-layer-rim',
                        area: ['450px', '350px'],
                        content: '/toEditClasses?c_id='+obj.data.c_id
                    });
                });

            }else if (layEvent === 'delete'){
                layui.use('layer', function() {
                    var layer = layui.layer;
                    layer.msg('你确定要删除该班级吗？', {
                        time: 0 //不自动关闭
                        , btn: ['确定', '取消']
                        , yes: function (index) {
                            window.location.href = "/deleteClasses?c_id="+obj.data.c_id;
                            layer.close(index);
                        }
                    });
                });
            }
        });
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'createClass':
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.open({
                            type: 2,
                            scrollbar:false,
                            resize:false,
                            skin: 'layui-layer-rim',
                            area: ['450px', '350px'],
                            content: '/toEditClasses?c_id=0'
                        });
                    });
                    break;
            };
        });
    });
</script>

</body>
</html>
