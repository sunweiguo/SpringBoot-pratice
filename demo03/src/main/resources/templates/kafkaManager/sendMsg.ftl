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
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="firstname" class="col-sm-2 control-label">kafka的地址</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="address"
                                       placeholder="请输入kafka的地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">kafka的端口</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="port"
                                       placeholder="请输入kafka的端口">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">发送的工单</label>
                            <div class="col-sm-8">
                                <textarea type="text" class="form-control" id="sendmsg" rows="10" placeholder="请输入发送的工单"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">发送消息</button>
                            </div>
                        </div>
                    </form>
                </div>
                <ul class="nav nav-list"><li class="divider"></li></ul>
                <form class="form-horizontal" role="form">
                    <div class="col-md-12 column">
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">接收的工单</label>
                            <div class="col-sm-8">
                                <textarea type="text" class="form-control" id="receivemsg" rows="10"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>