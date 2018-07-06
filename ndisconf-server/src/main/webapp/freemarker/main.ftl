<#include "/basehead.ftl">

<#include "/head.ftl">

<div id="Wrapper" class="clearfix">
    <div id="Main" style="margin-top:50px;">
        <div class="container-fluid">
            <div class="row-fluid" style="min-height:400px;">
                <div class="sidebar span2" style="float:left;overflow:visible;">

					<#include "/leftbar.ftl">
					
                </div>
                
                <div class="span10" style="float:left">

                    <div class="row-fluid">
                        <div class="contentWrap clearfix">
                            <ul id="versionChoice" class="nav nav-tabs" role="tablist"></ul>
                        </div>
                    </div>

                    <div id="zk_deploy" class="row-fluid" style="margin-bottom:5px">
                                <span id="env_info" href="#" class="muted" style="float:left" title="" type="">
                                </span>
                                <span id="app_info" href="#" class="muted" style="float:left" title="" type="">
                                </span>
                        <a id="zk_deploy_button" href="#" class="btn-small btn-primary" style="float:right;" title=""
                           type="button">
                            <b>ZK部署情况</b>
                        </a>
                        <a id="batch_download" href="#" class="btn-small btn-primary"
                           style="float:right;margin-right:5px;" title="" type="button">
                            <b>批量下载</b>
                        </a>
                    </div>

                    <div id="zk_deploy_info" class="hide" style="padding-bottom:20px;">
                                <pre id="zk_deploy_info_pre" style="min-height:200px">
                                </pre>
                    </div>

                    <div class="row-fluid">

                        <div class="contentWrap clearfix">
                            <div id="mainlist_error" class="alert alert-warning" role="alert"></div>
                            <table id="mainlist"
                                   class="table table-bordered table-condensed table-hover account-tableWrap table-striped"
                                   style="display:none">
                                <thead>
                                <tr>
                                    <td>#</td>
                                    <td>APP</td>
                                    <td>KEY</td>
                                    <td>配置内容</td>
                                    <td>实例列表</td>
                                    <td>修改时间</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody id="accountBody"
                                       style="max-width:760px; overflow-wrap: break-word;word-wrap: break-word;"
                                       class="account-tableWrap"></tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- 详细信息单行模板-->
                <div id="tbodyTpl" class="hide" style="width:0px;">
                    <table>
                        <tr>
                            <td>{12}</td>
                            <td style="max-width:100px">{1}</td>
                            <td style="max-width:100px">{6} &nbsp; {5}</td>
                            <td>{14}</td>
                            <td>{15}</td>
                            <td>{8}</td>
                            <td>{10} &nbsp; {11} &nbsp; {13} &nbsp; </td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>


<#include "/foot.ftl">
<script src="/static/assets/js/mainList.js"></script>
<#include "/basefoot.ftl">

