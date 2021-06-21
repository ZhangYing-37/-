<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-17
  Time: 20:30
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
    <link rel="stylesheet" href="/exam/css/icon.css">
    <link rel="stylesheet" href="/exam/css/public.css"/>
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
        <div class="layui-side-scroll" >
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
                <%--<li class="layui-nav-item">
                    <a href="javascript:;">班级管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray;">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-col-md10 paperSO">
                <div class="layui-card" id="title">
                    <div class="layui-card-header">
                        <h2 class="layui-col-md12" id="paperTitle" style="text-align: center;" >${thisPaper.p_title}</h2>
                    </div>
                    <div class="layui-card-body" id="paperDesc">说明：${thisPaper.p_desc}</div>
                </div>
            </div>
            <div class="layui-col-md10 paperSO" style="margin-top: 0px">
                <div class="layui-card" id="user">
                    <div class="layui-card-header">
                        <h2 class="layui-col-md12" id="userCode" style="font-size: 20px">学号：${sessionScope.thisPaperUser.u_code}</h2>
                    </div>
                    <div class="layui-card-body" id="userName" style="font-size: 20px;border-bottom: 1px solid lightgray;">姓名：${sessionScope.thisPaperUser.u_realName}</div>
                </div>
            </div>

            <c:if test="${sessionScope.correctPaper.subjectList.get(0).sj_id ne null}">

                <form class="layui-form layui-form-pane" lay-filter="score" method="post" id="score">
                    <c:set var="i" value="0" scope="session"></c:set>
                    <c:forEach var="subject" items="${sessionScope.correctPaper.subjectList}" varStatus="status">
                    <div class="layui-col-md10 paperSO" style="margin-top: 0px" id="paperS${subject.s_code}">
                        <div class="layui-card" id="subject${subject.s_code}">
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
                                <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>

                                <span class="layui-col-md3" style="float: right;margin-top: 3px">
                                    <label class="layui-form-label" style="width: 80px;">得分：</label>
                                    <div class="layui-input-inline">
                                        <c:if test="${subject.s_type eq 1}">
                                            <div style="width: 80px">
                                                <select name="subject${subject.s_code}" lay-verify="">
                                                <option value="0">0分</option>
                                                <c:if test="${sessionScope.userScore.get(status.index).score eq subject.s_score}">
                                                    <option value="${subject.s_score}" selected>${subject.s_score}分</option>
                                                </c:if>
                                                <c:if test="${sessionScope.userScore.get(status.index).score ne subject.s_score}">
                                                    <option value="${subject.s_score}">${subject.s_score}分</option>
                                                </c:if>
                                            </select>
                                            </div>

                                        </c:if>
                                        <c:if test="${subject.s_type eq 2}">
                                            <div style="width: 80px">
                                                <select name="subject${subject.s_code}" lay-verify="" style="width: 50px">
                                                    <option value="0">0分</option>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score eq sessionScope.correctPaper.DXscore}">
                                                        <option value="${sessionScope.correctPaper.DXscore}" selected>${sessionScope.correctPaper.DXscore}分</option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score ne sessionScope.correctPaper.DXscore}">
                                                        <option value="${sessionScope.correctPaper.DXscore}">${sessionScope.correctPaper.DXscore}分</option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score eq subject.s_score}">
                                                        <option value="${subject.s_score}" selected>${subject.s_score}分</option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score ne subject.s_score}">
                                                        <option value="${subject.s_score}">${subject.s_score}分</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${subject.s_type eq 3}">
                                            <div style="width: 80px">
                                                <select name="subject${subject.s_code}" lay-verify="" style="width: 50px">
                                                    <option value="0">0分</option>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score eq subject.s_score}">
                                                        <option value="${subject.s_score}" selected>${subject.s_score}分</option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.userScore.get(status.index).score ne subject.s_score}">
                                                        <option value="${subject.s_score}">${subject.s_score}分</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${subject.s_type eq 4}">
                                            <div class="layui-input-inline" style="width: 80px">
                                                <input type="number" name="subject${subject.s_code}" id="subject${subject.s_code}" value="${sessionScope.userScore.get(status.index).score}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                                            </div>
                                        </c:if>
                                        <c:if test="${subject.s_type eq 5}">
                                            <div class="layui-input-inline" style="width: 80px">
                                                <input type="number" name="subject${subject.s_code}" id="subject${subject.s_code}" value="${sessionScope.userScore.get(status.index).score}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                                            </div>
                                        </c:if>
                                    </div>
                                </span>

                            </div>
                            <div class="layui-card-body" style="border-bottom: 1px solid lightgray;">
                                <c:if test="${subject.s_type eq 1}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 11}">
                                                <li><i class="layui-icon layui-icon-radio"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 21}">
                                                <li><i class="layui-icon layui-icon-circle"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 12}">
                                                <li><i class="layui-icon layui-icon-radio"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 22}">
                                                <li><i class="layui-icon layui-icon-circle"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 2}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 11}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang2"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 21}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang1"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 12}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang2"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 22}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang1"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 3}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 11}">
                                                <li><i class="layui-icon layui-icon-ok-circle"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 21}">
                                                <li><i class="layui-icon layui-icon-circle"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 12}">
                                                <li><i class="layui-icon layui-icon-ok-circle"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 22}">
                                                <li><i class="layui-icon layui-icon-circle"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 4}">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">学生答案：</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${subject.optionList.get(0).o_name}" class="layui-input" disabled>
                                        </div>
                                        <label class="layui-form-label">正确答案：</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${sessionScope.thisPaper.subjectList.get(status.index).optionList.get(0).o_name}" class="layui-input" disabled>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${subject.s_type eq 5}">
                                    <span>
                                        <textarea class="layui-textarea" maxlength="200" disabled>${sessionScope.correctPaper.resultList.get(i).result}</textarea>
                                        <c:set var="i" scope="session" value="${i+1}"></c:set>
                                    </span>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </c:forEach>
                    <div class="layui-col-md10 paperSO" style="margin-top: 0px">
                        <div class="layui-card">
                            <div class="layui-card-body" style="height: 40px;">
                                <span style="margin-left: 35%">
                                    <button class="layui-btn layui-btn-lg" id="getSumbit" lay-submit="" lay-filter="formCorrectPaper">批改完成</button>
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        <div class="layui-row layui-col-space10">
            <strong class="layui-col-md3" style="margin-left: 10%;" id="totalScore">总分：${sessionScope.totalScore}分</strong>
            <c:if test="${sessionScope.thisPaper.subjectList.get(0).sj_id eq null}">
                <strong class="layui-col-md3">试卷题目总数：0</strong>
            </c:if>
            <c:if test="${sessionScope.thisPaper.subjectList.get(0).sj_id ne null}">
                <strong class="layui-col-md3">试卷题目总数：${sessionScope.thisPaper.subjectList.size()}</strong>
            </c:if>
        </div>
    </div>
</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script src="/exam/js/public.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(formCorrectPaper)', function (data) {
            console.log(data.field);
            $.ajax({
                url:"/correctPaperByTeacher",
                type:"post",
                async:false,
                data: JSON.stringify(data.field),
                contentType:"application/json",
                dataType:"text",
                success:function (data) {
                    window.location.href = "/toAnalysis?p_id="+data;
                }
            })
            return false;
        });
    });
    var int=self.setInterval("totalScore()",10000);
    function totalScore() {
        var form = layui.form;
        var datas = form.val('score');
        console.log(datas);
        var totalScore=0;
        for (var key in datas){
            var item=datas[key];
            if (item!=null&&item!=""){
                item=parseFloat(item);
                totalScore = totalScore+item;
            }
        }
        $('#totalScore').text("总分："+totalScore+"分");

    }
</script>
</body>
</html>