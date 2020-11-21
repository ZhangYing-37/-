
<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-06
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
</head>
<body>
<div style="margin: 5% 5%; width: 450px; height: 200px;">
    <form class="layui-form layui-form-pane" action="/updateSubjectTK" method="post">
        <input type="hidden" name="sj_id" id="sj_id" value="${sessionScope.thisSubject.sj_id}">
        <div class="layui-form-item layui-form-text">
            <c:if test="${sessionScope.thisSubject.s_code ne null}">
                <label class="layui-form-label">（第${sessionScope.thisSubject.s_code}题）填空题题目：</label>
                <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisSubject.s_code}">
            </c:if>
            <c:if test="${sessionScope.thisSubject.s_code eq null}">
                <c:if test="${sessionScope.thisPaper.subjectList.get(i).sj_id eq null}">
                    <label class="layui-form-label">（第${sessionScope.thisPaper.subjectList.size()}题）填空题题目：</label>
                    <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisPaper.subjectList.size()}">
                </c:if>
                <c:if test="${sessionScope.thisPaper.subjectList.get(i).sj_id ne null}">
                    <label class="layui-form-label">（第${sessionScope.thisPaper.subjectList.size()+1}题）填空题题目：</label>
                    <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisPaper.subjectList.size()+1}">
                </c:if>
            </c:if>
            <div class="layui-input-block">
                <textarea name="s_name" id="s_name" placeholder="请输入填空题题目" class="layui-textarea" maxlength="200">${sessionScope.thisSubject.s_name}</textarea>
            </div>
        </div>
        <div class="layui-form-item">

            <label class="layui-form-label">答案：</label>
            <div class="layui-input-inline">
                <input type="text" name="o_name" id="o_name" value="${sessionScope.thisSubject.optionList.get(0).o_name}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">题目分数：</label>
            <div class="layui-input-inline">
                <input type="number" name="s_score" id="s_score" required lay-verify="required" value="${sessionScope.thisSubject.s_score}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" id="getSumbit" style="margin-left: 10%" lay-submit="" lay-filter="formTK">保存</button>
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
        form.on('submit(formTK)', function (data) {
            console.log(data.field);
            var sj_id = data.field.sj_id;
            var s_code = data.field.s_code;
            var s_name = data.field.s_name;
            var o_name = data.field.o_name;
            var s_score = data.field.s_score;
            if (s_code==null){
                s_code = ${sessionScope.thisPaper.subjectList.size()+1};
            }
            if (sj_id==null){
                sj_id = 0;
            }
            //window.location.href = "/updateSubject?sj_id="+sj_id+"&s_code="+s_code+"&s_name="+s_name+"&o_name="+o_name+"&s_score="+s_score+"&s_type=4";
            $.ajax({
                url:"updateSubjectTK",
                type:"post",
                data:{
                    "sj_id":sj_id,
                    "s_code":s_code,
                    "s_name":s_name,
                    "o_name":o_name,
                    "s_score":s_score,
                    "s_type":4,
                },
                dataType:"text",
                success:function (data1) {
                    console.log("填空题返回值："+data1);
                }
            })
            window.parent.location.reload();
            parent.layer.close(index);
            return false;
        });
    })

</script>
</html>

