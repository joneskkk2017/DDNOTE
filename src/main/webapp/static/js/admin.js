$(function () {

    //折叠插件的状态
    //打开
    $(".collapse_all").on('shown.bs.collapse',function () {
       var a = $(this).prev(); //获取到上一个兄弟节点的dom对象
        var i = a.children();
        i.removeClass('icon-jiahao');
        i.addClass('icon-jianhao');
    });
    //收拢
    $(".collapse_all").on('hidden.bs.collapse',function () {
        var a = $(this).prev(); //获取到上一个兄弟节点的dom对象
        var i = a.children();
        i.removeClass('icon-jianhao');
        i.addClass('icon-jiahao');
    });

    $(".collapse_all > li > a").click(function (el) {
        el.preventDefault(); //禁用a标签原本的功能
        var $this = $(this);
        $(".collapse_all > li > a").removeClass("navactive");
        $this.addClass("navactive");
        $("#iframe-contant").attr('src',$this.data("iframesrc"));//+"?time=" + new Date().getTime()
        var text = $this.text();
        var mnav = $this.parent().parent().prev().text();
        $("#path_nav > .breadcrumb > li:last-child").html(text);
        $("#path_nav > .breadcrumb > li:last-child").prev().html(mnav);
    });

});
