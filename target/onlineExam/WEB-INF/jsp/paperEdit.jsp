<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-04
  Time: 10:49
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
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">密码修改</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/exitLogin">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll" >
            <ul class="layui-nav layui-nav-tree"  lay-filter="test" style="cursor: pointer">
                <li class="layui-nav-item"><a onclick="toUpdateSubjectDX1(0)">添加单选题<span class="layui-badge">单选</span></a></li>
                <li class="layui-nav-item"><a onclick="toUpdateSubjectDX(0)">添加多选题<span class="layui-badge layui-bg-orange">多选</span></a></li>
                <li class="layui-nav-item"><a onclick="toUpdateSubjectPD(0)">添加判断题<span class="layui-badge layui-bg-green">判断</span></a></li>
                <li class="layui-nav-item"><a onclick="toUpdateSubjectTK(0)">添加填空题<span class="layui-badge layui-bg-blue">填空</span></a></li>
                <li class="layui-nav-item"><a onclick="toUpdateSubjectJD(0)">添加简答题<span class="layui-badge layui-bg-gray">简答</span></a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: lightgray;">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-col-md10 paperSO">
                <div class="layui-card" id="title" onmouseover="setColor('title')" onmouseout="removeColor('title')" onclick="toUpdate()">
                    <div class="layui-card-header">
                        <h2 class="layui-col-md12" id="paperTitle" style="text-align: center;" >${thisPaper.p_title}</h2>
                    </div>
                    <div class="layui-card-body" id="paperDesc">说明：${thisPaper.p_desc}</div>
                </div>
            </div>

            <c:if test="${sessionScope.thisPaper.subjectList.get(0).sj_id ne null}">
                <c:forEach var="subject" items="${sessionScope.thisPaper.subjectList}" varStatus="status">
                    <div class="layui-col-md10 paperSO" id="paperS${subject.s_code}">
                        <div class="layui-card" id="subject${subject.s_code}">
                            <div class="layui-card-header" style="height: auto">
                                <c:if test="${subject.s_type eq 1}">
                                    <span class="layui-badge">单选</span>
                                    <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>
                                </c:if>
                                <c:if test="${subject.s_type eq 2}">
                                    <span class="layui-badge layui-bg-orange">多选</span>
                                    <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>
                                </c:if>
                                <c:if test="${subject.s_type eq 3}">
                                    <span class="layui-badge layui-bg-green">判断</span>
                                    <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>
                                </c:if>
                                <c:if test="${subject.s_type eq 4}">
                                    <span class="layui-badge layui-bg-blue">填空</span>
                                    <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>
                                </c:if>
                                <c:if test="${subject.s_type eq 5}">
                                    <div style="height: auto">
                                        <span class="layui-badge layui-bg-cyan">简答</span>
                                        <span class="s_code">${subject.s_code}、</span><span>${subject.s_name}（分值:${subject.s_score}分）</span>
                                    </div>
                                </c:if>


                            </div>
                            <div class="layui-card-body">
                                <c:if test="${subject.s_type eq 1}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 1}">
                                                <li><i class="layui-icon layui-icon-radio"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 2}">
                                                <li><i class="layui-icon layui-icon-circle"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 2}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 1}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang2"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 2}">
                                                <li><i class="layui-icon layui-icon-fuxuankuang1"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 3}">
                                    <ul style="margin-left: 5%;">
                                        <c:forEach var="option" items="${subject.optionList}">
                                            <c:if test="${option.o_isTrue eq 1}">
                                                <li><i class="layui-icon layui-icon-ok-circle"></i><span>${option.o_name}（正确答案）</span></li>
                                            </c:if>
                                            <c:if test="${option.o_isTrue eq 2}">
                                                <li><i class="layui-icon layui-icon-close-fill"></i><span>${option.o_name}</span></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${subject.s_type eq 4}">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">正确答案：</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${subject.optionList.get(0).o_name}" class="layui-input" disabled>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${subject.s_type eq 5}">
                                    <div><span style="margin-left: 5%;">正确答案：无（由教师批改）</span></div>
                                </c:if>

                            </div>
                            <div class="layui-card-body" style="height: 31px;">
                                <div class="layui-btn-container" style="float: right;">
                                    <c:if test="${subject.s_type eq 1}">
                                        <button type="button" onclick="toUpdateSubjectDX1(${subject.sj_id})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                    </c:if>
                                    <c:if test="${subject.s_type eq 2}">
                                        <button type="button" onclick="toUpdateSubjectDX(${subject.sj_id})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                    </c:if>
                                    <c:if test="${subject.s_type eq 3}">
                                        <button type="button" onclick="toUpdateSubjectPD(${subject.sj_id})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                    </c:if>
                                    <c:if test="${subject.s_type eq 4}">
                                        <button type="button" onclick="toUpdateSubjectTK(${subject.sj_id})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                    </c:if>
                                    <c:if test="${subject.s_type eq 5}">
                                        <button type="button" onclick="toUpdateSubjectJD(${subject.sj_id})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>编辑</button>
                                    </c:if>
                                    <button type="button" onclick="deleteSubject(${subject.sj_id},${subject.s_code})" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-delete"></i>删除</button>
                                    <button type="button" onclick="moveUp(${subject.s_code},${sessionScope.thisPaper.subjectList.size()})" id="moveUp${subject.s_code}" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-up"></i>上移</button>
                                    <button type="button" onclick="moveDown(${subject.s_code},${sessionScope.thisPaper.subjectList.size()})" id="moveDown${subject.s_code}" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-down"></i>下移</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <a name="end"></a>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        <div class="layui-row layui-col-space10">
            <strong class="layui-col-md2" style="margin-left: 5%;">试卷总分：${sessionScope.totalScore}分</strong>
            <c:if test="${sessionScope.thisPaper.subjectList.get(0).sj_id eq null}">
                <strong class="layui-col-md2">试卷题目总数：0</strong>
            </c:if>
            <c:if test="${sessionScope.thisPaper.subjectList.get(0).sj_id ne null}">
                <strong class="layui-col-md2">试卷题目总数：${sessionScope.thisPaper.subjectList.size()}</strong>
            </c:if>
            <span class="layui-col-md2" style="float: right;">
			<button type="button" onclick="endEdit(${sessionScope.thisPaper.co_id})" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-ok"></i>完成编辑</button>
		</span>
        </div>
    </div>
</div>
<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script src="/exam/js/paperEdit.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>