$(function () {

    //提交按钮
    $("#addUserBtn").on("click",function () {

        //提交表单数据
        $("#addUserForm").submit();
    });

    //清除模态框缓存,点哪个显示哪个,模态框关闭后,清空内容
    $("#addUserModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
        $("input").value('');
    });
    //清除模态框缓存,点哪个显示哪个,模态框关闭后,清空内容
    $("#updateUserModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });

    //
    $("#updateUserModal").on("shown.bs.modal",function () {
        $("#updateUserModal .selectpicker").selectpicker();
    });

    //批量删除
    $("#batchDelUsersBtn").on("click",function () {
        //1.获取到勾选了的复选框的dom对象,集合变量里
        var checkboxes = $(".chkone:checked");
        if(checkboxes.length == 0){
            alert("必须至少勾选一个用户");
        }else{
            //2.获取到勾选了的复选框中的value(user的id值)
            var userIds = new Array();
            checkboxes.each(function () {
                userIds.push(this.value);
            });
          //3.通过ajax向服务端提交批量删除用户的请求
            //转换成json格式
            var datas = JSON.stringify(userIds);
            var flag = delAllSure();
             if(flag) {
                 $.ajax({
                     url: "/DDNOTE_war_exploded/admin/batchDelUsers.html",
                     type: 'POST',
                     data: {uid: datas},
                     success: function (rs) {

                        if(rs == "success"){
                            alert("批量删除成功!");
                            $(location).attr("href","/DDNOTE_war_exploded/admin/userManager.html");
                        }
                     }
                 });
             }
        }
    });

    //查询按钮
    $("#searchBtn").on("click",function () {
        $("#search_form").submit();
    });

    //模态框中获取焦点
    $("#addUserModal").on("shown.bs.modal",function () {
        $("#inp1").focus();
    });

    //模态框中获取焦点
    $("#updateUserModal").on("shown.bs.modal",function () {
        $("#inp2").focus();
    });
});

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

//编辑用户按钮
function updateUserFormSubmit() {
    $("#updateUserForm").submit();
}

//确定是否删除用户
function delSure() {

    if(confirm("确定要删除这个用户吗?")){
        return true;
    }else {
        return false;
    }
}

function delAllSure() {

    if(confirm("确定要删除这个用户吗?")){
        return true;
    }else {
        return false;
    }
}