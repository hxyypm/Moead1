<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- layui.css -->
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style type="text/css">
        .layui-form-label {
            width: 150px;
        }

        p {
            margin: 0;
            padding: 0;
        }

        .layui-input-block {
            margin-left: 150px;
        }

        .tag,
        .tag-defined {
            display: inline-block;
            position: relative;
            padding: 0 20px;
            border: 1px solid #DDD;
            border-radius: 2px;
            cursor: pointer;
            line-height: 36px;
            margin: 0 10px 10px 0;
        }

        .tag-selected {
            border: 1px solid #5FB878;
            color: #5FB878;
        }

        .tick-box {
            display: none;
        }

        .tag .tick-bg {
            position: absolute;
            right: 0;
            bottom: 0;
            border: 10px solid;
            border-color: transparent #5FB878 #5FB878 transparent;
        }

        .tag .tick {
            position: absolute;
            right: 0;
            bottom: -12px;
            font-size: 10px;
            color: #FFF;
        }

        .imgbox {
            line-height: 120px;
            height: 120px;
            width: 120px;
        }
    </style>
</head>
<body>

<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form" class="layui-form " lay-filter="form">
                    <div class="layui-form-item">
                        <input type="hidden" name="id" value="${algorithmic.id}" />
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-block">
                            <div id="editormd">
                                <textarea style="display:none;" name="content">${algorithmic.content}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item" style="margin-bottom: 150px">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<!-- layui.js -->
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<!-- layui规范化用法 -->
<#--<script type="text/javascript" charset="utf-8" src="${ctx!}/js/plugins/ueditor/ueditor.config.js"></script>-->
<#--<script type="text/javascript" charset="utf-8" src="${ctx!}/js/plugins/ueditor/ueditor.all.min.js"> </script>-->

<link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/lib/codemirror/codemirror.min.css"/>
<!--全局Js-->
<script type="text/javascript" src="${ctx!}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx!}/js/plugins/editor/editormd.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/css/editormd.css"/>
<script type="text/javascript">
    //实例化编辑器
    // var ue = UE.getEditor('content');
    var testEditor;
    $(function () {
        testEditor = editormd("editormd", {
            // width   : "1000px",
            height: 1000,
            syncScrolling: "single",
            path: "${ctx!}/js/plugins/editor/lib/",
            // theme : "dark",
            // previewTheme : "dark",
            // editorTheme : "pastel-on-dark",
            // markdown : md,
            codeFold: true,
            saveHTMLToTextarea: false,    // 保存 HTML 到 Textarea
            searchReplace: true,
            //watch : false,                // 关闭实时预览
            htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
            //toolbar  : false,             //关闭工具栏
            //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
            emoji: true,
            taskList: true,
            tocm: true,         // Using [TOCM]
            tex: true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart: true,             // 开启流程图支持，默认关闭
            sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"]
        });
    });
    layui.config({
        base : "${ctx!}/js/"
    }).use(['form','layer','jquery', 'element','upload'], function () {
        var $ = layui.jquery,
                form = layui.form,
                upload = layui.upload,
                layer = layui.layer;


        form.on('submit(add)', function (data) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/algorithmic/update",
                data: data.field,
                success: function(ret){
                    if(ret.isOk){
                        layer.msg("操作成功", {time: 2000},function(){
//                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//                            parent.layer.close(index);
//                            window.parent.location.href="/algorithmic/index";
                        });
                    }else{
                        layer.msg(ret.message, {time: 2000});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>

</html>