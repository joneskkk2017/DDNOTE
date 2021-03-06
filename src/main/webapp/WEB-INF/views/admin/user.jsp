<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/9/25
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户管理</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/font/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/user.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="search_title_bar">
            搜索
        </div>
    </div>
    <div id="search1" class="row">
        <form id="search_form" class="form-inline" action="${pageContext.request.contextPath}/admin/userSearch.html" method="post">
            <div class="form-group">
                <label>用户名:</label>
                <input class="form-control input-sm" type="text" name="userInfo" placeholder="请输入用户名">
            </div>
        </form>
    </div>
    <div id="search_btn" class="row text-left">
        <button id="searchBtn" type="button" class="btn btn-default">查询</button>
        <button type="button" data-toggle="modal" data-target="#addUserModal" class="btn btn-default">添加</button>
        <button id="batchDelUsersBtn" type="button" class="btn btn-default">删除</button>
<%--        <button type="button" class="btn btn-default">导入</button>--%>
<%--        <button type="button" class="btn btn-default">导出</button>--%>
    </div>
    <div id="content_table" class="row">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <tr id="table1">
                    <td style="width: 6px;"><input id="allcheck" type="checkbox" name="allcheck" class="chkall"
                                                   onclick="chkall();" /></td>
                    <td>用户名</td>
                    <td>密码</td>
                    <td>手机号</td>
                    <td>部门</td>
                    <td>区域</td>
                    <td>角色</td>
                    <td>用户状态</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${allUsers.list}" var="user">
                <tr>
                    <td><input value="${user.id}" type="checkbox" name="chkuser" class="chkone" onclick="chkone();" /></td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.telephone}</td>
                    <td>${user.department.departname}</td>
                    <td>${user.area.areaname}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role">
                            ${role.name}&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${user.enable==1}">激活</c:if>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/admin/updateUser.html?id=${user.id}" data-toggle="modal" data-target="#updateUserModal">编辑</a>
                        <a href="${pageContext.request.contextPath}/admin/delUser.html?id=${user.id}" onclick="return delSure()">删除</a></td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div id="pager" class="row">
        <p class="pull-left">总共有<span> ${allUsers.total} </span>条记录,当前页<span> ${allUsers.pageNum} / ${allUsers.pages} </span>页</p>
        <div class="btngroup pull-right">
            <a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=1&pageSize=10" type="button" class="btn btn-default">首页</a>
            <a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${allUsers.prePage}&pageSize=10" type="button" class="btn btn-default">上一页</a>
            <a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${allUsers.nextPage}&pageSize=10" type="button" class="btn btn-default">下一页</a>
            <a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${allUsers.pages}&pageSize=10" type="button" class="btn btn-default">尾页</a>
        </div>
    </div>
</div>

<%-- Modal 添加模态框 --%>
<div class="modal fade" id="addUserModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModal1">添加用户</h4>
            </div>
            <div class="modal-body">
                <form id="addUserForm" action="${pageContext.request.contextPath}/admin/addUser.html" method="post">
                    <div class="form-group" >
                        <label>用户名:</label>
                        <input id="inp1" type="text" name="username" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>密 码:</label>
                        <input type="password" name="password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>手 机:</label>
                        <input type="password" name="telephone" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>部 门:</label>
                        <select  name="departmentId" class="selectpicker form-control">  //支持多选,后面加multiple  自带搜索功能data-live-search="true"
                            <c:forEach items="${allDepartments}" var="Department">
                                <option value="${Department.id}">${Department.departname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>区 域:</label>
                        <select name="areaId" class="selectpicker form-control">  //支持多选,后面加multiple  自带搜索功能data-live-search="true"
                            <c:forEach items="${allAreas}" var="area1">
                                <option value="${area1.id}">${area1.areaname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>角色:</label>
                        <select name="roleIds" class="selectpicker form-control" multiple>  //支持多选,后面加multiple  自带搜索功能data-live-search="true"
                            <c:forEach items="${allRoles}" var="role">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="addUserBtn" type="button" class="btn btn-primary">添加用户</button>
            </div>
        </div>
    </div>
</div>

<%-- Modal 编辑模态框 --%>
<div class="modal fade" id="updateUserModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">


        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/user.js"></script>
<script src="${pageContext.request.contextPath}/select/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>