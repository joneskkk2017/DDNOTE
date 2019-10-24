$(function () {

    $("#addRoleBtn").on("click",function () {

        $("#addRoleForm").submit();
    });


    //清除模态框缓存,点哪个显示哪个,模态框关闭后,清空内容
    $("#updateRoleModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });
    //重新刷新模态框的selectpicker插件
    $("#updateRoleModal").on("shown.bs.modal",function () {
        $("#updateRoleModal .selectpicker").selectpicker();
    });
    //模态框中获取焦点
    $("#addRoleModal").on("shown.bs.modal",function () {
        $("#inp1").focus();
    });

    //模态框中获取焦点
    $("#updateRoleModal").on("shown.bs.modal",function () {
        $("#inp2").focus();
    });
});

//编辑角色按钮
function updateRoleFormSubmit() {
    $("#updateRoleForm").submit();
}

//确定是否删除用户
function delSure() {

    if(confirm("确定要删除这个角色吗?")){
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