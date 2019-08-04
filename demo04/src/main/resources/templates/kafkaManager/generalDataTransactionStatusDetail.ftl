<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>回执工单ID</th>
                            <th>回执工单关联ID</th>
                            <th>状态</th>
                            <th>地区</th>
                            <th>回执时间</th>
                            <th>描述</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${generalDataTransactionStatus.workid!'空'}</td>
                            <td>${generalDataTransactionStatus.correlateid!'空'}</td>
                            <td>${generalDataTransactionStatus.status!'空'}</td>
                            <td>${generalDataTransactionStatus.areano!'空'}</td>
                            <td>${generalDataTransactionStatus.feedbackdate!'空'}</td>
                            <td>${generalDataTransactionStatus.resultdescription!'空'}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>