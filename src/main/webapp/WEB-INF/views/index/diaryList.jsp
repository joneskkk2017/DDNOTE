<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/10/14
  Time: 20:19
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
    <link href="${pageContext.request.contextPath}/static/css/diaryList.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/layDate-v5.0.9/layDate-v5.0.9/laydate/laydate.js"></script>

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
                <li><a data-toggle="modal" data-target="#writeDiaryModal" style="cursor:hand;">撰写日志</a></li>
                <li><a data-toggle="modal" data-target="#writeModal" style="cursor:hand;">修改密码</a></li>
                <li><a href="${pageContext.request.contextPath}/index/diaryCount.html" method="get">日志统计</a></li>
<%--                <li><a href="${pageContext.request.contextPath}/index/diaryCount.html">日志统计</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/logout.html">退出</a></li>
                <li><a href="#">欢迎 <strong>< <shiro:principal property="username"/> ></strong></a></li>
            </ul>
        </div>
    </div>
</nav>
<%--表格列表--%>
<div class="container">
    <div id="content_table" class="row">
        <div class="col-md-3"></div>
        <div class="col-md-18">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <tr>
                    <th>
                 <div class="input-group">
                    <form id="searchdiary_form" class="form-inline" action="${pageContext.request.contextPath}/index/searchdiary.html" method="post" style="height: 46.8px;line-height: 46.8px;margin-bottom: 0px;text-align: center">
                            <label>选择日期:</label>
                            <input id="test1" class="input-sm" type="text" name="diaryDate" placeholder="请选择日期" size="6" autocomplete="off" readonly="readonly">
                            <input class="btn btn-default" type="submit" value="查阅">
                    </form>
                 </div>
                    </th>
<%--                    <th style="text-align: center;height: 46.8px;line-height: 46.8px;">--%>
<%--                        日志查询 : <input size="3" type="text" id="test1" class="input-sm" />--%>
<%--                        <button id="searchDiaryByDateBtn" class="btn btn-default btn-sm">查阅</button>--%>
<%--                    </th>--%>
                    <th style="text-align: center;height: 46.8px;line-height: 46.8px;"><${user.username}> 日志列表</th>
                    <th colspan="3"></th>
                </tr>
                <tr id="table1">
                    <td style="text-align: center"><strong>日期</strong></td>
                    <td style="text-align: center"><strong>标题</strong></td>
                    <td style="text-align: center"><strong>批阅</strong></td>
                    <td style="text-align: center"><strong>点击数</strong></td>
                    <td style="text-align: center"><strong>地址</strong></td>
                </tr>
<%--                pattern="yyyy年MM月dd日 HH:mm:ss"--%>
                <c:forEach items="${diaryList.list}" var="diary">
                    <tr>
                        <td style="text-align: center"><fmt:formatDate value="${diary.addDate}" type="both" dateStyle="full" /></td>
                        <td style="text-align: center"><a href="${pageContext.request.contextPath}/index/checkContent.html?id=${diary.id}&uname=<shiro:principal property="username"/>" data-toggle="modal" data-target="#checkContentModal">${diary.diarytitle}</a></td>
                        <td style="text-align: center">
                            <c:if test="${diary.readable==0}">---</c:if>
                            <c:if test="${diary.readable==1}">已批阅</c:if>
                        </td style="text-align: center">
                        <td style="text-align: center">${diary.clickcount}</td>
                        <td style="text-align: center">
                            <c:if test="${fn:contains(diary.ipaddress,'10.10.80.')}">
                                总部外网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.10.')}">
                                总部内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.20.')}">
                                总部内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.60.')}">
                                总部内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.80.')}">
                                总部内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.1.')}">
                                天海内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.25.')}">
                                东源内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.11.')}">
                                鸿翔内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.16.')}">
                                东滢内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.6.')}">
                                东滢内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.9.')}">
                                鸿晟内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.7.')}">
                                柬东渡内网
                            </c:if>
                            <c:if test="${fn:contains(diary.ipaddress,'192.168.8.')}">
                                柬太平洋内网
                            </c:if>
                        </td>
<%--                        <td><a href="${pageContext.request.contextPath}/admin/updateArea.html?id=${ar.id}" data-toggle="modal" data-target="#updateAreaModal">编辑</a>--%>
<%--                            <a href="${pageContext.request.contextPath}/admin/delArea.html?id=${ar.id}" onclick="return delSure()">删除</a></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
    <div id="pager" class="row">
        <div class="col-md-5"></div>
        <div class="col-md-14">
        <p class="pull-left">总共有<span> ${diaryList.total} </span>条记录,当前页<span> ${diaryList.pageNum} / ${diaryList.pages} </span>页</p>
        <div class="btngroup pull-right">
            <a href="${pageContext.request.contextPath}/index/diaryList.html?pageNum=1&pageSize=31" type="button" class="btn btn-default">首页</a>
            <a href="${pageContext.request.contextPath}/index/diaryList.html?pageNum=${diaryList.prePage}&pageSize=31" type="button" class="btn btn-default">上一页</a>
            <a href="${pageContext.request.contextPath}/index/diaryList.html?pageNum=${diaryList.nextPage}&pageSize=31" type="button" class="btn btn-default">下一页</a>
            <a href="${pageContext.request.contextPath}/index/diaryList.html?pageNum=${diaryList.pages}&pageSize=31" type="button" class="btn btn-default">尾页</a>
        </div>
    </div>
    </div>
<div class="col-md-5"></div>
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
                    <input id="inp3" type="text" name="" class="form-control" value="工作日志"/>
                </div>
                <div class="form-group">
                    <label>内容:</label>
                    <%--                        <input type="password" name="passwordConfirm" class="form-control" />--%>
                    <!-- 加载编辑器的容器 -->
                    <script id="myEditor1" name="content" type="text/plain"></script>
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
<%-- Modal 查看日志内容 --%>
<div class="modal fade" id="checkContentModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

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
<script src="${pageContext.request.contextPath}/static/js/diaryList.js"></script>

<%--配置文件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<%--配置源码文件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script>

    var ue = UE.getEditor('myEditor1',{
        initialFrameWidth:null,
        initialFrameHeight:425
    });
</script>
</body>
</html>
