<#include "/basehead.ftl">
<#include "/head.ftl">

<div id="Wrapper" class="clearfix">
    <div id="Main" style="margin-top:50px;">
        <div class="container-fluid">

            <div class="row-fluid">
                <ul class="breadcrumb" style="background-color:#fff;">
                    <li>
                        <a href="/main">配置</a> /
                    </li>
                    <li>
                        <span id="currentData"></span>
                    </li>
                    <li class="active">
                        / 配置文件修改
                    </li>
                </ul>
            </div>

            <div class="row-fluid">

                <div class="span4">
                    <div class="well sidebar-nav" style="padding:10px;background-color:#fff">
                        <ul id="sidebarcur" class="nav nav-list"></ul>
                    </div>
                    <!--/.well -->
                </div>

                <div class="span8">

                    <div class="content" style="padding-bottom:15px;">

                        <form id="form" action="/config/" method="post" enctype="multipart/form-data"
                              class="private-form clearfix">
                            <h2 class="autoPush-detail-title">配置文件修改</h2>

                            <p class="private-item">
                                <span class="private-item-key">APP id & 名称：</span>
                                <span id="app" class="private-item-value">FFFF</span>
                            </p>

                            <p class="private-item">
                                <span class="private-item-key">版本：</span>
                                <span id="version" class="private-item-value">FFFF</span>
                            </p>

                            <p class="private-item">
                                <span class="private-item-key">环境：</span>
                                <span id="env" class="private-item-value">FFFF</span>
                            </p>

                            <p class="private-item">
                                <span class="private-item-key">配置文件名：</span>
                                <span id="key" class="private-item-value">FFFF</span>
                            </p>

                            <div class="private-item clearfix">
                                <span style="float:left" class="private-item-key">原配置文件内容：</span>
                                <pre style="float:left" id="oldvalue" class="private-item-value-pre">FFFF</pre>
                            </div>

                            <div class="private-item">
                                <span class="private-item-key">修改方式：</span>

                                <div class="btn-group">
                                    <a id="uploadChoiceA" class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                        <span style="margin-right:2px;">选择上传方式</span><span class="caret"></span> </a>
                                    <ul class="dropdown-menu" id="uploadChoice">
                                        <li>
                                            <a href="#">输入文本</a>
                                        </li>
                                        <li>
                                            <a href="#">上传配置文件</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div id="file_upload_choice" class="private-item clearfix hide">
                                <span style="float:left" class="private-item-key">上传配置文件：</span>

                                <div style="float:left;width: 300px" class="private-item-value-pre">
                                    <span class="sl-custom-file btn btn-success fileinput-button"
                                          style="overflow: hidden;cursor: pointer;">
                                        <div id="open_dialog_rar">
                                           	 上传配置文件...
                                        </div>
                                        <input class="ui-input-file" type="file" name="myfilerar" id="myfilerar"
                                               style="width:120px;height:30px;">
                                    </span>
                                    <br/>
                                    <span id="error_rar" class="text-error error_rar"></span>

                                    <div style="color: #999;">
                                       	 仅支持任意类型配置文件(.properties文件可支持自动注入, 非.properties文件则只是简单托管)
                                        <span id="status_rar" class="text-success"></span>
                                    </div>
                                    <div class="progress_rar">
                                        <div class="bar_rar"></div>
                                        <div class="percent_rar">
                                            0%
                                        </div>
                                    </div>
                                    <div class="loading_rar"></div>
                                </div>
                            </div>

                            <div id="text_upload_choice" class="hide">

                                <div class="private-item">
                                    <span style="float:left" class="private-item-key">输入文本：</span>
                                    <textarea id="fileContent" type="text" class="private-item-value"
                                              style="min-width:500px;height:200px;"></textarea>
                                </div>

                            </div>

                            <div id="error" class="error hide">
                                	表单选项不能为空或填写格式错误！
                            </div>

                        </form>

                        <div>
                            <button id="file_submit" class="private-submit">
                                	上传
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 详细信息单行模板-->
<div id="tItemTpl" class="hide">
    <li style="font-size:13px;overflow-wrap: break-word;word-wrap: break-word;" class="{2}">
        <a href="{1}">{0}</a>
    </li>
</div>



<#include "/foot.ftl">
<script src="/static/assets/js/modifycommon.js"></script>
<script src="/static/assets/js/jquery.form.js"></script>
<script src="/static/assets/js/modifyFile.js"></script>
<#include "/basefoot.ftl">
