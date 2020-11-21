<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-12
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>在线考试系统</title>
    <link rel="stylesheet" href="/exam/layui/css/layui.css">
    <link rel="stylesheet" href="/exam/css/icon.css">
    <link rel="stylesheet" href="/exam/css/public.css"/>
</head>

<body style="background-color: lightblue;">
<div id="paperContent" style="text-align: left;">
    <div class="survey">
        <div class="surveyHead">
            <div class="layui-card-header" style="border-bottom: none;">
                <h1>${sessionScope.thisPaper.p_title}</h1>
            </div>
            <div class="layui-card-body" style="border-bottom: 1px solid #F6F6F6;">
                ${sessionScope.thisPaper.p_desc}
            </div>
        </div>
        <form class="layui-form layui-form-pane" method="post">
            <div class="surveySubject">
                <c:forEach var="subject" items="${sessionScope.thisPaper.subjectList}">
                    <div class="layui-card-header" style="border-bottom: none;">
                        <c:if test="${subject.s_type eq 1}">
                            <span class="layui-badge">单选</span>
                        </c:if>
                        <c:if test="${subject.s_type eq 2}">
                            <span class="layui-badge layui-bg-orange">多选</span>
                        </c:if>
                        <c:if test="${subject.s_type eq 3}">
                            <span class="layui-badge layui-bg-green">判断</span>
                        </c:if>
                        <c:if test="${subject.s_type eq 4}">
                            <span class="layui-badge layui-bg-blue">填空</span>
                        </c:if>
                        <c:if test="${subject.s_type eq 5}">
                            <span class="layui-badge layui-bg-cyan">简答</span>
                        </c:if>
                        <span>${subject.s_code}、${subject.s_name}（分值:${subject.s_score}分）</span>

                    </div>
                    <div class="layui-card-body" style="border-bottom: 1px solid #F6F6F6;">
                        <div class="layui-form-item">
                            <c:if test="${subject.s_type eq 1}">
                                <ul>
                                    <c:forEach var="option" items="${subject.optionList}" varStatus="status">
                                        <li><input type="radio" name="subject${subject.s_code}" value="${option.o_id}"><span>${option.o_name}</span></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${subject.s_type eq 2}">
                                <ul>
                                    <c:forEach var="option" items="${subject.optionList}" varStatus="status">
                                        <li><input type="checkbox" name="subject${subject.s_code}option${status.count}" value="${option.o_id}" lay-skin="primary"><span>${option.o_name}</span></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${subject.s_type eq 3}">
                                <ul>
                                    <c:forEach var="option" items="${subject.optionList}" varStatus="status">
                                        <li><input type="radio" name="subject${subject.s_code}" value="${option.o_id}"><span>${option.o_name}</span></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${subject.s_type eq 4}">
                                <label class="layui-form-label">答案：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="subject${subject.s_code}"  placeholder="请输入填空题答案" autocomplete="off" class="layui-input">
                                </div>
                            </c:if>
                            <c:if test="${subject.s_type eq 5}">
                                <textarea name="subject${subject.s_code}"  placeholder="请输入答案" class="layui-textarea" maxlength="200"></textarea>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
                <div style="margin-top: 10px; height: 60px">
                    <span style="margin-left: 30%">
                        <button class="layui-btn layui-btn-lg" id="getSumbit" style="margin-left: 10%" lay-submit="" lay-filter="formWritePaper">提交试卷</button>
                    </span>
                </div>
            </div>
        </form>

    </div>

</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(formWritePaper)', function (data) {
            console.log(data.field);
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.load();
                setTimeout(function(){
                    layer.closeAll('loading');
                }, 500);
            });
            $.ajax({
                url:"addResult",
                type:"post",
                async:false,
                data: JSON.stringify(data.field),
                contentType:"application/json",
                dataType:"text",
                success:function (data) {
                    console.log("co_id="+data);
                    window.location.href = "/toPaperListUser?co_id="+data;
                }
            })
            return false;
        });
    });
</script>

</body>
</html>
