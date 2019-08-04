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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>工单id</th>
                            <th>工单关联id</th>
                            <th>状态</th>
                            <th>描述信息</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list generalDataTransactionStatusPage.content as generalDataTransactionStatus>
                            <tr>
                                <td>${generalDataTransactionStatus.workid}</td>
                                <td>${generalDataTransactionStatus.correlateid}</td>
                                <td><#if generalDataTransactionStatus.status == 0>正常<#else>不正常</#if></td>
                                <td>${generalDataTransactionStatus.resultdescription}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/demo04/receiveMsgManager/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..generalDataTransactionStatusPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <#--<li class="disabled"><a href="#">${index}</a></li>-->
                                <li class="page-item active "><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li><a href="/demo04/receiveMsgManager/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte generalDataTransactionStatusPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/demo04/receiveMsgManager/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>