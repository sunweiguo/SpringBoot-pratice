<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label"><font color="red"><b>*</b></font>kafka的地址</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="address" name="address"
                                       placeholder="请输入kafka的地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label"><font color="red"><b>*</b></font>kafka的端口</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="port" name="port"
                                       placeholder="请输入kafka的端口">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="topic" class="col-sm-2 control-label"><font color="red"><b>*</b></font>kafka的topic</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="topic" name="topic"
                                       placeholder="请输入kafka的topic">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sendmsg" class="col-sm-2 control-label"><font color="red"><b>*</b></font>发送的工单</label>
                            <div class="col-sm-8">
                                <textarea type="text" class="form-control" id="sendmsg" name="sendmsg" rows="10" placeholder="请输入发送的工单"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" id="send" class="btn btn-default">发送消息</button>
                            </div>
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" id="check" class="btn btn-default">查看回执消息</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    $("#send").click(function(){
        //1.获取所有要提交的内容
        var _address = $("#address").val();
        var _port = $("#port").val();
        var _topic = $("#topic").val();
        var _sendmsg = $("#sendmsg").val();

        $.ajax({
            type : 'POST' ,
            url : "/demo03/sendMsgManager/doSendMsg",
            data : {
                address : _address,
                port : _port,
                topic : _topic,
                sendmsg : _sendmsg
            },
            success : function(data) {
                if(data.code != 0)
                {
                    alert(data.msg);
                }
                else
                {
                   alert("success");
                }
            },
            error : function (data) {
                console.log("哪里出问题了...请联系管理员");
            }
        })

    })
</script>
</html>