<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-19
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>设置</title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
</head>
<body>
<div style="margin: 5% 5%; width: 500px; height: 200px;">
    <form class="layui-form layui-form-pane" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: auto;">考试开始时间：</label>
            <div class="layui-input-inline">
                <input type="datetime-local" name="startTime" value="${sessionScope.thisPaper.startTime}"  required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: auto;">考试结束时间：</label>
            <div class="layui-input-inline">
                <input type="datetime-local" name="endTime" value="${sessionScope.thisPaper.endTime}" required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: auto;">多选题选项少于正确选项数量时得分：</label>
            <div class="layui-input-inline">
                <input type="number" name="DXscore" value="${sessionScope.thisPaper.DXscore}" style="width: 70px;" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <button class="layui-btn" id="getSumbit" style="margin-left: 10%" lay-submit="" lay-filter="formSetting">发布考试</button>
    </form>
</div>
</body>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script>
    layui.use('form', function(){
        var index = parent.layer.getFrameIndex(window.name);
        var form = layui.form;
        form.on('submit(formSetting)', function (data) {
            console.log(data.field);
            $.ajax({
                url:"/updatePaperSetting",
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
