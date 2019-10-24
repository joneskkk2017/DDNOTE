<%--
  Created by IntelliJ IDEA.
  User: JoNeS
  Date: 2019/9/16
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工日志系统</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/font/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/admin.css" rel="stylesheet" type="text/css">
    <script language="JavaScript">
        function startTime()
        {
            var today=new Date();//定义日期对象
            var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年
            var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年
            var dd = today.getDate();//通过日期对象的getDate()方法返回年
            var hh=today.getHours();//通过日期对象的getHours方法返回小时
            var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟
            var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒
            // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09
            MM=checkTime(MM);
            dd=checkTime(dd);
            mm=checkTime(mm);
            ss=checkTime(ss);
            var day; //用于保存星期（getDay()方法得到星期编号）
            if(today.getDay()==0)   day   =   "星期日 "
            if(today.getDay()==1)   day   =   "星期一 "
            if(today.getDay()==2)   day   =   "星期二 "
            if(today.getDay()==3)   day   =   "星期三 "
            if(today.getDay()==4)   day   =   "星期四 "
            if(today.getDay()==5)   day   =   "星期五 "
            if(today.getDay()==6)   day   =   "星期六 "
            document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day;
            setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法
        }

        function checkTime(i)
        {
            if (i<10){
                i="0" + i;
            }
            return i;
        }
    </script>
</head>
<body onload="startTime()">
<%-- 顶部菜单栏 --%>
    <div id="admin_top" class="container-fluid">
        <div class="row">
            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-header col-md-8">
                    <span class="navbar-brand"><i class="iconfont icon-xitongguanli"></i>员工日志后台管理页面</span>
                </div>
                <div class="col-md-8 col-md-offset-8 login_info text-right">
                    <i class="iconfont icon-yonghu"></i><shiro:principal property="username"/>
                    <i class="iconfont icon-rili"></i><span id="nowDateTimeSpan"></span></font>
                    <a href="${pageContext.request.contextPath}/logout.html" class="pull-right"><i class="iconfont icon-dianyuan"></i></a>
                </div>
            </nav>
        </div>
    </div>
<%-- 左边栏 --%>
    <div id="sidle_bar">
        <div class="sidlebar_title">
            <p>
                <span>导航模块 / </span>
                <span> Nav Module</span>
            </p>
        </div>
        <div class="sidlebar_content navbar-fixed-bottom">
            <a href="#collapse_system" data-toggle="collapse"><i class="iconfont icon-jianhao"></i>系统设置</a>
            <ul id="collapse_system" class="collapse in collapse_all">
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/userManager.html?pageNum=1&pageSize=10"><i class="iconfont icon-tubiaozhizuo-"></i>用户管理</a></li>
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/roleManager.html?pageNum=1&pageSize=10"><i class="iconfont icon-tubiaozhizuo-"></i>角色管理</a></li>
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/resourceManager.html?pageNum=1&pageSize=10"><i class="iconfont icon-tubiaozhizuo-"></i>资源管理</a></li>
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/deptManager.html?pageNum=1&pageSize=10"><i class="iconfont icon-tubiaozhizuo-"></i>部门管理</a></li>
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/areaManager.html?pageNum=1&pageSize=10"><i class="iconfont icon-tubiaozhizuo-"></i>区域管理</a></li>
            </ul>
            <a href="#collapse_log" data-toggle="collapse"><i class="iconfont icon-jianhao"></i>笔记管理</a>
            <ul id="collapse_log" class="collapse in collapse_all">
                <li><a href="#" data-iframesrc="${pageContext.request.contextPath}/admin/diaryManager.html"><i class="iconfont icon-tubiaozhizuo-"></i>笔记操作</a></li>
<%--                <li><a href="#"><i class="iconfont icon-tubiaozhizuo-"></i>用户管理</a></li>--%>
            </ul>
        </div>
    </div>
<%-- 路径导航 --%>
    <div id="path_nav">
        <ol class="breadcrumb">
            <li><a href="#">后台首页</a></li>
            <li style="color:#337ab7;">系统设置</li>
            <li class="active">系统信息</li>
        </ol>
    </div>
<%-- 右边栏 --%>

<script src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/select/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>
<iframe id="iframe-contant" name="mainFrame" class="navbar-fixed-bottom" frameborder="no" scrolling="auto" width="100%" height="100%"
        allowtransparency="true" src="${pageContext.request.contextPath}/static/html/admin_welcome.html"></iframe>