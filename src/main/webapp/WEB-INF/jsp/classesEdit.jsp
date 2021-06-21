<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-20
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>班级管理</title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
</head>
<body>
<div style="margin: 5% 5%; width: 400px; height: 200px;">
    <form class="layui-form layui-form-pane" method="post">
        <input type="hidden" name="c_id" value="${thisClasses.c_id}">
        <div class="layui-form-item">
            <label class="layui-form-label">年级：</label>
            <div class="layui-input-inline">
                <input type="number" name="c_grade" value="${thisClasses.c_grade}" style="width: 100px;"  required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">班级号：</label>
            <div class="layui-input-inline">
                <input type="text" name="c_code" value="${thisClasses.c_code}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="c_name" value="${thisClasses.c_name}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <button class="layui-btn" id="getSumbit" style="margin-left: 10%" lay-submit="" lay-filter="formClasses">完成编辑</button>
    </form>
</div>
</body>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script>
    layui.use('form', function(){
        var index = parent.layer.getFrameIndex(window.name);
        var form = layui.form;
        form.on('submit(formClasses)', function (data) {
            console.log(data.field);
            $.ajax({
                url:"/editClasses",
                type:"post",
                async:false,
                data: JSON.stringify(data.field),
                contentType:"application/json",
                dataType:"text",
                success:function (data) {
                    parent.location.reload();
                    parent.layer.close(index);
                }
            })
            return false;
        });
    });
</script>
</html>
