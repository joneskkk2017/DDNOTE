$(function () {

    $("#searchBtn").on("click",function () {

        $("#search_form").submit();
    });

});


//确定是否删除日志
function delSure() {

    if(confirm("确定要删除这条工作笔记吗?")){
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