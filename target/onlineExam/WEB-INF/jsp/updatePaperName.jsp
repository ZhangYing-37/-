<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-05
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
</head>
<body>
<div style="margin: 5% 5%; width: 400px; height: 200px;">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">标题：</label>
            <div class="layui-input-inline">
                <input type="text" name="paperName" id="paperName" value="${sessionScope.thisPaper.p_title}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明：</label>
            <div class="layui-input-block">
                <textarea name="desc" id="desc" placeholder="请输入" class="layui-textarea" maxlength="200">${sessionScope.thisPaper.p_desc}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" id="getSumbit" lay-submit="" lay-filter="formDemo">提交</button>
        </div>
    </form>
</div>

</body>

<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name);

    function closeThis(){
        parent.layer.close(index);
    };
    layui.use('form', function(){
        var form = layui.form
        form.on('submit(formDemo)', function (data) {
            console.log(data.field);
            var title = data.field.paperName;
            var desc = data.field.desc;
            window.parent.document.getElementById("paperTitle").innerHTML = title;
            window.parent.document.getElementById("paperDesc").innerHTML = "说明："+desc;
            window.location.href = "/updatePaperND?paperTitle="+title+"&paperDesc="+desc;
            parent.layer.close(index);
        });
    })

</script>
</html>

