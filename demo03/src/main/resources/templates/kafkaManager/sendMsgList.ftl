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
                            <th>topic</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list generalDataTransactionPage.content as generalDataTransaction>
                            <tr>
                                <td>${generalDataTransaction.workid}</td>
                                <td>${generalDataTransaction.systemid}</td>
                                <td>${generalDataTransaction.topic}</td>
                                <td><a href="/demo03/sendMsgList/detail?workid=${generalDataTransaction.workid}">详情</a></td>
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
                            <li><a href="/demo03/sendMsgList/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..generalDataTransactionPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <#--<li class="disabled"><a href="#">${index}</a></li>-->
                                <li class="page-item active "><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li><a href="/demo03/sendMsgList/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte generalDataTransactionPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/demo03/sendMsgList/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>