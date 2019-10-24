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
    <title>区域管理</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/font/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/area.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div id="search_btn">
        <button type="button" data-toggle="modal" data-target="#addAreaModal" class="btn btn-default">添加</button>
    </div>
    <div id="content_table" class="row">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <tr id="table1">
                    <td style="width: 6px;"><input id="allcheck" type="checkbox" name="allcheck" class="chkall"
                                                   onclick="chkall();" /></td>
                    <td>区域名称</td>
                    <td>区域排序字段</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${allArea.list}" var="ar">
                    <tr>
                        <td><input value="${ar.id}" type="checkbox" name="chkuser" class="chkone" onclick="chkone();" /></td>
                        <td>${ar.areaname}</td>
                        <td>${ar.areasort}</td>
                        <td><a href="${pageContext.request.contextPath}/admin/updateArea.html?id=${ar.id}" data-toggle="modal" data-target="#updateAreaModal">编辑</a>
                            <a href="${pageContext.request.contextPath}/admin/delArea.html?id=${ar.id}" onclick="return delSure()">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div id="pager" class="row">
        <p class="pull-left">总共有<span> ${allArea.total} </span>条记录,当前页<span> ${allArea.pageNum} / ${allArea.pages} </span>页</p>
        <div class="btngroup pull-right">
            <a href="${pageContext.request.contextPath}/admin/areaManager.html?pageNum=1&pageSize=10" type="button" class="btn btn-default">首页</a>
            <a href="${pageContext.request.contextPath}/admin/areaManager.html?pageNum=${allArea.prePage}&pageSize=10" type="button" class="btn btn-default">上一页</a>
            <a href="${pageContext.request.contextPath}/admin/areaManager.html?pageNum=${allArea.nextPage}&pageSize=10" type="button" class="btn btn-default">下一页</a>
            <a href="${pageContext.request.contextPath}/admin/areaManager.html?pageNum=${allArea.pages}&pageSize=10" type="button" class="btn btn-default">尾页</a>
        </div>
    </div>
</div>
<%-- Modal 添加模态框 --%>
<div class="modal fade" id="addAreaModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModal1">添加区域</h4>
            </div>
            <div class="modal-body">
                <form id="addAreaForm" action="${pageContext.request.contextPath}/admin/addArea.html" method="post">
                    <div class="form-group">
                        <label>区域名称:</label>
                        <input id="inp1" type="text" name="areaname" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>区域排序字段:</label>
                        <input type="text" name="areasort" class="form-control" />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="addAreaBtn" type="button" class="btn btn-primary">添加区域</button>
            </div>
        </div>
    </div>
</div>
<%-- Modal 编辑模态框 --%>
<div class="modal fade" id="updateAreaModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/area.js"></script>
<script src="${pageContext.request.contextPath}/select/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>
