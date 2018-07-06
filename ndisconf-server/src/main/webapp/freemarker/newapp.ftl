<#include "/basehead.ftl">

<#include "/head.ftl">

<div id="Wrapper" class="clearfix">
    <div id="Main" style="margin-top:50px;">
        <div class="container-fluid">

            <div class="row-fluid">
                <ul class="breadcrumb" style="background-color:#fff;">
                    <li>
                        <a href="/app">应用</a>
                    </li>
                    <li class="active">
                        / 新建APP
                    </li>
                </ul>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="content oz">
                        <form id="form" class="private-form clearfix">
                            <h2 class="autoPush-detail-title">新建APP</h2>

                            <div class="private-item">
                                <span class="private-item-key">APP：</span>
                                <input id="app" type="text" class="private-item-value"/>
                            </div>

                            <div class="private-item">
                                <span class="private-item-key">说明：</span>
                                <textarea id="desc" type="text" class="private-item-value"
                                          style="width:400px;height:200px;"></textarea>
                            </div>

                            <div class="private-item">
                                <span class="private-item-key">Emails(;号分隔)：</span>
                                <textarea id="emails" type="text" class="private-item-value"
                                          style="width:400px;height:50px;"></textarea>
                            </div>

                            <div id="error" class="alert alert-warning hide" role="alert">
                                	表单选项不能为空或填写格式错误！
                            </div>
                            <div class="private-item" style="text-align:center">
                                <a id="item_submit" class="btn btn-primary"> 新建 </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            
            
        </div>
    </div>
</div>

<#include "/foot.ftl">

<script src="/static/assets/js/newapp.js"></script>

<#include "/basefoot.ftl">
