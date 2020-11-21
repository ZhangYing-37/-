<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2020-11-11
  Time: 16:07
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
    <form class="layui-form layui-form-pane" action="/updateSubjectDX1" method="post">
        <input type="hidden" name="sj_id" id="sj_id" value="${sessionScope.thisSubject.sj_id}">
        <div class="layui-form-item layui-form-text">
            <c:if test="${sessionScope.thisSubject.s_code ne null}">
                <label class="layui-form-label">（第${sessionScope.thisSubject.s_code}题）单选题题目：</label>
                <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisSubject.s_code}">
            </c:if>
            <c:if test="${sessionScope.thisSubject.s_code eq null}">
                <c:if test="${sessionScope.thisPaper.subjectList.get(i).sj_id eq null}">
                    <label class="layui-form-label">（第${sessionScope.thisPaper.subjectList.size()}题）单选题题目：</label>
                    <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisPaper.subjectList.size()}">
                </c:if>
                <c:if test="${sessionScope.thisPaper.subjectList.get(i).sj_id ne null}">
                    <label class="layui-form-label">（第${sessionScope.thisPaper.subjectList.size()+1}题）单选题题目：</label>
                    <input type="hidden" name="s_code" id="s_code" value="${sessionScope.thisPaper.subjectList.size()+1}">
                </c:if>
            </c:if>

            <div class="layui-input-block">
                <textarea name="s_name" id="s_name" placeholder="请输入单选题题目" required lay-verify="required" class="layui-textarea" maxlength="200">${sessionScope.thisSubject.s_name}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <table width="450px">
                <thead style="background-color: lightgray;">
                <tr>
                    <th width="64%">选项内容</th>
                    <th width="16%">正确答案</th>
                    <th width="10%">上移</th>
                    <th width="10%">下移</th>
                </tr>
                </thead>

                <tbody style="background-color: whitesmoke;">
                <c:if test="${sessionScope.thisSubject.optionList.size() lt 2 or sessionScope.thisSubject eq null}">
                    <tr height="30px" id="option1">
                        <td align="left">
                            <span><input type="text" name="o_name1" id="o_name1" value="选项1" style="width: 200px;" required lay-verify="required"></span>
                            <span id="optionAdd1" onclick="addTR(1,2)" class="layui-icon layui-icon-add-circle" style="cursor: pointer; font-size: 20px;"></span>
                            <span id="optionRemove1" onclick="removeTR(1,2)" class="layui-icon layui-icon-reduce-circle" style="cursor: pointer;  font-size: 20px;"></span>
                        </td>
                        <td align="center">
                            <span><input name="isTrue" id="isTrue1" type="radio" value="1" lay-skin="primary"></span>
                        </td>
                        <td align="center"><span id="optionUp1" onclick="optionUp(1,2)" class="layui-icon layui-icon-upload-circle" style="cursor: pointer; font-size: 17px;"></span></td>
                        <td align="center"><span id="optionDown1" onclick="optionDown(1,2)" class="layui-icon layui-icon-download-circle" style="cursor: pointer; font-size: 20px;"></span></td>
                    </tr>
                    <tr height="30px" id="option2">
                        <td align="left">
                            <span><input type="text" name="o_name2" id="o_name2" value="选项2" style="width: 200px;" required lay-verify="required"></span>
                            <span id="optionAdd2" onclick="addTR(2,2)" class="layui-icon layui-icon-add-circle" style="cursor: pointer; font-size: 20px;"></span>
                            <span id="optionRemove2" onclick="removeTR(2,2)" class="layui-icon layui-icon-reduce-circle" style="cursor: pointer;  font-size: 20px;"></span>
                        </td>
                        <td align="center">
                            <span><input name="isTrue" id="isTrue2" type="radio" value="2" lay-skin="primary" checked></span>
                        </td>
                        <td align="center"><span id="optionUp2" onclick="optionUp(2,2)" class="layui-icon layui-icon-upload-circle" style="cursor: pointer; font-size: 17px;"></span></td>
                        <td align="center"><span id="optionDown2" onclick="optionDown(2,2)" class="layui-icon layui-icon-download-circle" style="cursor: pointer; font-size: 20px;"></span></td>
                    </tr>
                </c:if>
                <c:forEach var="option" items="${sessionScope.thisSubject.optionList}" varStatus="status">
                    <tr height="30px" id="option${status.count}">
                        <td align="left">
                            <span><input type="text" name="o_name${status.count}" id="o_name${status.count}" value="${option.o_name}" style="width: 200px;" required lay-verify="required"></span>
                            <span id="optionAdd${status.count}" onclick="addTR(${status.count},${sessionScope.thisSubject.optionList.size()})" class="layui-icon layui-icon-add-circle" style="cursor: pointer; font-size: 20px;"></span>
                            <span id="optionRemove${status.count}" onclick="removeTR(${status.count},${sessionScope.thisSubject.optionList.size()})" class="layui-icon layui-icon-reduce-circle" style="cursor: pointer;  font-size: 20px;"></span>
                        </td>
                        <td align="center">
                            <c:if test="${option.o_isTrue eq 1}">
                                <span><input name="isTrue" id="isTrue${status.count}" type="radio" value="${status.count}" lay-skin="primary" checked></span>
                            </c:if>
                            <c:if test="${option.o_isTrue ne 1}">
                                <span><input name="isTrue" id="isTrue${status.count}" type="radio" value="${status.count}" lay-skin="primary"></span>
                            </c:if>
                        </td>
                        <td align="center"><span id="optionUp${status.count}" onclick="optionUp(${status.count},${sessionScope.thisSubject.optionList.size()})" class="layui-icon layui-icon-upload-circle" style="cursor: pointer; font-size: 17px;"></span></td>
                        <td align="center"><span id="optionDown${status.count}" onclick="optionDown(${status.count},${sessionScope.thisSubject.optionList.size()})" class="layui-icon layui-icon-download-circle" style="cursor: pointer; font-size: 20px;"></span></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">题目分数：</label>
            <div class="layui-input-inline">
                <input type="number" name="s_score" id="s_score" value="${sessionScope.thisSubject.s_score}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" id="getSumbit" style="margin-left: 10%" lay-submit="" lay-filter="formDX1">保存</button>
        </div>
    </form>
</div>

</body>

<script src="/exam/layui/layui.js"></script>
<script src="/exam/js/jquery.js"></script>
<script src="/exam/js/updateSubjectDX1.js"></script>

</html>
