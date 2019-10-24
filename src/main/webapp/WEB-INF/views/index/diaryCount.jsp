<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/10/11
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="${pageContext.request.contextPath}/static/css/diaryCount.css" rel="stylesheet" type="text/css">
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
            <a href="#" class="navbar-brand"><img src="${pageContext.request.contextPath}/static/image/logo1.png" alt="江苏东渡纺织集团" ></a>
        </div>
        <div id="dd_navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/index/index1.html">首页</a></li>
                <li><a data-toggle="modal" data-target="#writeModal" style="cursor:hand;">修改密码</a></li>
                <%--                <li><a data-toggle="modal" data-target="#diaryCountModal" style="cursor: hand;">日志统计</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/index/diaryCount.html" method="get">日志统计</a></li>
                <li><a href="${pageContext.request.contextPath}/logout.html">退出</a></li>
                <li><a href="#">欢迎 <b>< <shiro:principal property="username"/> ></b></a></li>
            </ul>
        </div>
    </div>
</nav>
<%--表格列表--%>
<div class="container">
    <div id="content_table" class="row">
        <div class="col-md-5"></div>
        <div class="col-md-14">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <tr>
                    <th>
<%--                        <div id="search2" class="row" style="float:left;margin-left: 20%;">--%>
<%--                        <form id="searchdiaryCount_form" class="form-inline" action="${pageContext.request.contextPath}/index/searchdiaryCount.html" method="post" style="height: 46.8px;line-height: 46.8px;margin-bottom: 0px;text-align: center">--%>
<%--                            <div class="form-group">--%>
<%--                                <label>选择日期:</label>--%>
<%--                                <input id="test1" class="form-control input-sm" type="text" name="diaryDate" placeholder="请选择日期" size="6" autocomplete="false">--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <button id="searchdiaryCountBtn" class="btn btn-default" type="text" style="margin-left:20px;">查询</button>--%>
<%--                        </div>--%>

        <div class="input-group">
            <form id="searchdiaryCount_form" class="form-inline" action="${pageContext.request.contextPath}/index/searchdiaryCount.html" method="post" style="height: 46.8px;line-height: 46.8px;margin-bottom: 0px;text-align: center">
                <input id="test1" type="text" class="form-control" name="diaryDate" placeholder="请选择日期" style="margin-top: 8px;"  autocomplete="off" readonly="readonly">
            </form>
            <span class="input-group-btn">
                <button id="searchdiaryCountBtn" class="btn btn-default" type="button" style="margin-top: 4px;"><strong>查询</strong></button>
            </span>
        </div><!-- /input-group -->
                    </th>
                    <%--                    <th style="text-align: center;height: 46.8px;line-height: 46.8px;">--%>
                    <%--                        日志查询 : <input size="3" type="text" id="test1" class="input-sm" />--%>
                    <%--                        <button id="searchDiaryByDateBtn" class="btn btn-default btn-sm">查阅</button>--%>
                    <%--                    </th>--%>
                    <th style="text-align: center;height: 46.8px;line-height: 46.8px;">日志列表</th>
                </tr>
                <tr id="table1">
                    <td style="text-align: center"><strong>姓名</strong></td>
                    <td style="text-align: center"><strong>日志统计</strong></td>
                </tr>
                <%--                pattern="yyyy年MM月dd日 HH:mm:ss"--%>
                <c:forEach items="${diaryCount.list}" var="diaryc">
                    <tr>
                        <td style="text-align: center">${diaryc.username}</td>
                        <td style="text-align: center">${diaryc.rptNum}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </div>
    </div>
    <div class="col-md-5"></div>
    </div>
    <div id="pager" class="row">
        <div class="col-md-5"></div>
        <div class="col-md-14">
        <div>
        <p class="pull-left">总共有<span> ${diaryCount.total} </span>条记录,当前页<span> ${diaryCount.pageNum} / ${diaryCount.pages} </span>页</p>
        <div class="btngroup pull-right">
            <a href="${pageContext.request.contextPath}/index/searchdiaryCount.html?pageNum=1&pageSize=20" type="button" class="btn btn-default">首页</a>
            <a href="${pageContext.request.contextPath}/index/searchdiaryCount.html?pageNum=${diaryCount.prePage}&pageSize=20&diaryDate=${diaryForDate}" type="button" class="btn btn-default">上一页</a>
            <a href="${pageContext.request.contextPath}/index/searchdiaryCount.html?pageNum=${diaryCount.nextPage}&pageSize=20&diaryDate=${diaryForDate}" type="button" class="btn btn-default">下一页</a>
            <a href="${pageContext.request.contextPath}/index/searchdiaryCount.html?pageNum=${diaryCount.pages}&pageSize=20&diaryDate=${diaryForDate}" type="button" class="btn btn-default">尾页</a>
        </div>
        </div>
        </div>
        <div class="col-md-5"></div>
    </div>
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

<%-- Modal 日志统计 --%>
<div class="modal fade" id="diaryCountModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/diaryCount.js"></script>
<script src="${pageContext.request.contextPath}/layDate-v5.0.9/layDate-v5.0.9/laydate/laydate.js"></script>
<script>
    //执行一个laydate实例
    laydate.render({
        elem: '#test1', //指定元素
    });
</script>
</body>
</html>
