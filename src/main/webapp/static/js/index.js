$(function () {

    //提交按钮
    $("#changeNewBtn").on("click", function () {
        // var x = $("#inp1").val();
        // var y = $("#passwordConfirm").val();
        //提交表单数据
        $("#changeNewForm").submit();
    });

    $("#writeModal").on("shown.bs.modal",function () {
        $("#inp1").focus();
    });

    $("#writeModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });

    $("#checkContentModal").on("hidden.bs.modal",function () {
        $(this).removeData("bs.modal");
    });

    window.UEDITOR_HOME_URL = "${pageContext.request.contextPath}/ueditor/";
    var ue = UE.getEditor('myEditor',{
        initialFrameWidth:null,
        initialFrameHeight:425,
    });

    $("#writeDiaryBtn").on("click",function () {

        var diarytitle = $("#inp3").val();
        //获取内容
        var diarycontent = ue.getContent();
        $('#writeDiaryModal').modal('hide');
        $("#writeDiaryModal").on("shown.bs.modal",function () {
            ue.setContent("");
        });
        $.ajax({
            url: "/DDNOTE_war_exploded/index/writeDiary.html",
            type: 'POST',
            data: {diarytitle:diarytitle,diarycontent:diarycontent},
            success: function (rs) {

                $("#writeDiaryModal").on("shown.bs.modal",function () {
                    ue.setContent("");
                });
            }
        });
    });

});