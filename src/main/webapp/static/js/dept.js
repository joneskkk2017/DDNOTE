$(function () {

    $("#addDeptBtn").on("click",function () {

        $("#addDeptForm").submit();
    });

    //清除模态框缓存,点哪个显示哪个,模态框关闭后,清空内容
    $("#updateDeptModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });

    $("#updateDeptModal").on("shown.bs.modal",function () {
        $("#inp2").focus();
    });

    //模态框中获取焦点
    $("#addDeptModal").on("shown.bs.modal",function () {
        $("#inp1").focus();
    });

});

//编辑角色按钮
function updateDeptFormSubmit() {
    $("#updateDeptForm").submit();
}

//确定是否删除用户
function delSure() {

    if(confirm("确定要删除这个部门吗?")){
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