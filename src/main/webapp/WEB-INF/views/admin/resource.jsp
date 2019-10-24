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
    <link href="${pageContext.request.contextPath}/static/css/resource.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div id="search_btn">
        <button type="button" data-toggle="modal" data-target="#addResourceModal" class="btn btn-default">添加</button>
    </div>
    <div id="content_table" class="row">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <tr id="table1">
                    <td style="width: 6px;"><input id="allcheck" type="checkbox" name="allcheck" class="chkall"
                                                   onclick="chkall();" /></td>
                    <td>资源描述</td>
                    <td>资源路径</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${allReses.list}" var="res">
                    <tr>
                        <td><input value="${res.id}" type="checkbox" name="chkuser" class="chkone" onclick="chkone();" /></td>
                        <td>${res.pathname}</td>
                        <td>${res.path}</td>
                        <td><a href="${pageContext.request.contextPath}/admin/updateResource.html?id=${res.id}" data-toggle="modal" data-target="#updateResourceModal">编辑</a>
                            <a href="${pageContext.request.contextPath}/admin/delResource.html?id=${res.id}" onclick="return delSure()">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div id="pager" class="row">
        <p class="pull-left">总共有<span> ${allReses.total} </span>条记录,当前页<span> ${allReses.pageNum} / ${allReses.pages} </span>页</p>
        <div class="btngroup pull-right">
            <a href="${pageContext.request.contextPath}/admin/resourceManager.html?pageNum=1&pageSize=10" type="button" class="btn btn-default">首页</a>
            <a href="${pageContext.request.contextPath}/admin/resourceManager.html?pageNum=${allReses.prePage}&pageSize=10" type="button" class="btn btn-default">上一页</a>
            <a href="${pageContext.request.contextPath}/admin/resourceManager.html?pageNum=${allReses.nextPage}&pageSize=10" type="button" class="btn btn-default">下一页</a>
            <a href="${pageContext.request.contextPath}/admin/resourceManager.html?pageNum=${allReses.pages}&pageSize=10" type="button" class="btn btn-default">尾页</a>
        </div>
    </div>
</div>
<%-- Modal 添加模态框 --%>
<div class="modal fade" id="addResourceModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModal1">添加资源</h4>
            </div>
            <div class="modal-body">
                <form id="addResourceForm" action="${pageContext.request.contextPath}/admin/addResource.html" method="post">
                    <div class="form-group">
                        <label>资源描述:</label>
                        <input id="inp1" type="text" name="pathname" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>资源路径:</label>
                        <input type="text" name="path" class="form-control" />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="addResourceBtn" type="button" class="btn btn-primary">添加资源</button>
            </div>
        </div>
    </div>
</div>
<%-- Modal 编辑模态框 --%>
<div class="modal fade" id="updateResourceModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/resource.js"></script>
<script src="${pageContext.request.contextPath}/select/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>
