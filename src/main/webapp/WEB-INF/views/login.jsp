<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/9/29
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="loginhtml" class="container">
    <div class="row">
        <!--  只占位置  -->
        <div class="col-md-7"></div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="${pageContext.request.contextPath}/login.html" method="post">
                        <legend class="text-center">用户登录</legend>
                        <div class="form-group">
                            <label>用户名:</label>
                            <input id="username" type="text" name="username" class="form-control" placeholder="请输入中文名">
                        </div>
                        <div class="form-group">
                            <label>密码:</label>
                            <input type="password" name="password" class="form-control" placeholder="请输入密码">
                        </div>
                        <div class="form-group">

                            <input type="submit" class="form-control btn-primary" value="登录">
                        </div>
                    </form>
                </div>
            </div>
            <!--  只占位置  -->
            <div class="col-md-7"></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/login.js"></script>
</body>
</html>
