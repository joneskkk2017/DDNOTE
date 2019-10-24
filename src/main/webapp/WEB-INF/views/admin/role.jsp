<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/9/25
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>角色管理</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/font/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/role.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container-fluid">
        <div id="search_btn">
            <button type="button" data-toggle="modal" data-target="#addRoleModal" class="btn btn-default">添加</button>
        </div>
        <div id="content_table" class="row">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <tr id="table1">
                        <td style="width: 6px;"><input id="allcheck" type="checkbox" name="allcheck" class="chkall"
                                                       onclick="chkall();" /></td>
                        <td>角色名</td>
                        <td>code</td>
                        <td>资源</td>
                        <td>角色代号</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${allRoles.list}" var="role">
                    <tr>
                        <td><input value="${role.id}" type="checkbox" name="chkuser" class="chkone" onclick="chkone();" /></td>
                        <td>${role.name}</td>
                        <td>${role.code}</td>
                        <td>
                            <c:forEach items="${role.resources}" var="rr">
                                ${rr.pathname}&nbsp;&nbsp;
                            </c:forEach>
                        </td>
                        <td>
                            ${role.idpath}
                        </td>

                        <td><a href="${pageContext.request.contextPath}/admin/updateRole.html?id=${role.id}" data-toggle="modal" data-target="#updateRoleModal">编辑</a>
                            <a href="${pageContext.request.contextPath}/admin/delRole.html?id=${role.id}" onclick="return delSure()">删除</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div id="pager" class="row">
            <p class="pull-left">总共有<span> ${allRoles.total} </span>条记录,当前页<span> ${allRoles.pageNum} / ${allRoles.pages} </span>页</p>
            <div class="btngroup pull-right">
                <a href="${pageContext.request.contextPath}/admin/roleManager.html?pageNum=1&pageSize=10" type="button" class="btn btn-default">首页</a>
                <a href="${pageContext.request.contextPath}/admin/roleManager.html?pageNum=${allRoles.prePage}&pageSize=10" type="button" class="btn btn-default">上一页</a>
                <a href="${pageContext.request.contextPath}/admin/roleManager.html?pageNum=${allRoles.nextPage}&pageSize=10" type="button" class="btn btn-default">下一页</a>
                <a href="${pageContext.request.contextPath}/admin/roleManager.html?pageNum=${allRoles.pages}&pageSize=10" type="button" class="btn btn-default">尾页</a>
            </div>
        </div>
    </div>
    <%-- Modal 添加模态框 --%>
    <div class="modal fade" id="addRoleModal" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h4 class="modal-title" id="myModal1">添加角色</h4>
                </div>
                <div class="modal-body">
                    <form id="addRoleForm" action="${pageContext.request.contextPath}/admin/addRole.html" method="post">
                        <div class="form-group" >
                            <label>角色名:</label>
                            <input id="inp1" type="text" name="name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>code:</label>
                            <input type="text" name="code" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>角色代号:</label>
                            <input type="text" name="idpath" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>资源列表:</label>
                            <select  name="resourcesIds" class="selectpicker form-control" multiple>  //支持多选,后面加multiple  自带搜索功能data-live-search="true"
                                <c:forEach items="${resourceList}" var="rlist">
                                    <option value="${rlist.id}">${rlist.pathname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="addRoleBtn" type="button" class="btn btn-primary">添加角色</button>
                </div>
            </div>
        </div>
    </div>
    <%-- Modal 编辑模态框 --%>
    <div class="modal fade" id="updateRoleModal" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/role.js"></script>
    <script src="${pageContext.request.contextPath}/select/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>
