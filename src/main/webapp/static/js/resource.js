$(function () {

    $("#addResourceBtn").on("click",function () {

        $("#addResourceForm").submit();
    });


    //清除模态框缓存,点哪个显示哪个,模态框关闭后,清空内容
    $("#updateResourceModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });

    //模态框中获取焦点
    $("#addResourceModal").on("shown.bs.modal",function () {
        $("#inp1").focus();
    });

    //模态框中获取焦点
    $("#updateResourceModal").on("shown.bs.modal",function () {
        $("#inp2").focus();
    });
});

//编辑角色按钮
function updateResourceFormSubmit() {
    $("#updateResourceForm").submit();
}

//确定是否删除用户
function delSure() {

    if(confirm("确定要删除这个资源吗?")){
        return true;
    }else {
        return false;
    }
}





//全选
function chkall() {
    $(".chkone").prop("checked",$(".chkall").prop("checked"));
}

function chkone() {
    var flag = true;
    $(".chkone").each(function (index,el) {
        var chk = $(el);
        if(chk.prop("checked") == false){
            flag = false;
        }
    });
    if(flag){
        $(".chkall").prop("checked",true);
    }else{
        $(".chkall").prop("checked",false);
    }
}