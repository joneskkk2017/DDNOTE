<%--
  Created by IntelliJ IDEA.
  User: 61044
  Date: 2019/9/3
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工日志系统</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/font/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- navbar-static-top:导航条变成直角 -->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#dd_navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand"><img src="${pageContext.request.contextPath}/static/image/logo1.png" ></a>
        </div>
        <div id="dd_navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/index/index1.html">首页</a></li>
                <li><a data-toggle="modal" data-target="#writeDiaryModal" style="cursor:hand;">撰写日志</a></li>
                <li><a data-toggle="modal" data-target="#writeModal" style="cursor:hand;">修改密码</a></li>
<%--                <li><a data-toggle="modal" data-target="#diaryCountModal" style="cursor: hand;">日志统计</a></li>--%>
                <shiro:hasAnyRoles name="chairman,gm,dgm"><li><a href="${pageContext.request.contextPath}/index/diaryCount.html" method="get">日志统计</a></li></shiro:hasAnyRoles>
                <li><a href="${pageContext.request.contextPath}/logout.html">退出</a></li>
                <li><a href="#">欢迎 <b>< <shiro:principal property="username"/> ></b></a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <c:forEach items="${areaList}" var="area">
    <div class="panel panel-default panel-info">
        <div class="panel-heading">
            <h3 class="panel-title"><span class="iconfont icon-bumen"></span>&nbsp;<b>${area.areaname}</b>:</h3>
        </div>
        <input type="hidden" value="${area.id}">
        <div class="panel-body">
            <c:forEach items="${users}" var="user">
                <c:if test="${area.id==user.areaid}">
                <div class="btn-group" role="group" aria-label="...">
                    <a type="button" class="btn btn-default iconfont icon-xiugaitouxiang" style="color:#337ab7;"></a>
                    <a type="button" class="btn btn-default zdy" href="${pageContext.request.contextPath}/index/diaryList.html?id=${user.userid}&pageNum=1&pageSize=31"><b>${user.username}</b></a>
                </div>
<%--                        <i class="icont icon-yuangong"></i>--%>
<%--                        <a class="btn btn-default" href="#?id=${user.id}" role="button">${user.username}</a>--%>
                </c:if>
            </c:forEach>
        </div>
    </div>
    </c:forEach>
</div>
<div class="footer hidden-xs">
    <div class="text-center"><h4>团结 忠诚 创新 务实</h4></div>
</div>

<%-- Modal 修改密码模态框 --%>
<div class="modal fade" id="writeModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModal1">修改密码</h4>
            </div>
            <div class="modal-body">
                <form id="changeNewForm" action="${pageContext.request.contextPath}/index/changeNewP.html" method="post">
                    <div class="form-group" >
                        <label>新密码:</label>
                        <input id="inp1" type="password" name="password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>确认新密码:</label>
                        <input id="passwordConfirm" type="password" name="passwordConfirm" class="form-control" />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="changeNewBtn" type="button" class="btn btn-primary">修改密码</button>
            </div>
        </div>
    </div>
</div>

<%-- Modal 写日志模态框 --%>
<div class="modal fade" id="writeDiaryModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModal2">撰写日志</h4>
            </div>
            <div class="modal-body">
<%--                <form id="writeDiaryForm" action="${pageContext.request.contextPath}/index/writeDiary.html" method="post">--%>
                    <div class="form-group" >
                        <label>标题:</label>
                        <input id="inp3" type="text" class="form-control" value="工作日志"/>
                    </div>
                    <div class="form-group">
                        <label>内容:</label>
<%--                        <input type="password" name="passwordConfirm" class="form-control" />--%>
                        <!-- 加载编辑器的容器 -->
                        <script id="myEditor" name="content" type="text/plain"></script>
                    </div>
<%--                </form>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="writeDiaryBtn" type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

<%-- Modal 日志统计 --%>
<div class="modal fade" id="diaryCountModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
<%--配置文件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<%--配置源码文件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<%--实例化编辑器--%>

</body>
</html>
