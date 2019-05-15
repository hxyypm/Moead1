<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 发布资源</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/css/editormd.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/lib/codemirror/codemirror.min.css" />
    <style type="text/css">
        .layui-form-item{
            margin: 50px 0 0 200px
        }
        .layui-form-item .layui-input-inline{
            width: 300px;
        }
    </style>
</head>

<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">发布资源</legend>
</fieldset>
<form class="layui-form layui-form-pane"  lay-filter="form">


    <div class="layui-form-item" style="visibility: hidden">
        <div class="layui-form-item">
            <input type="hidden" name="userId"  value="${(Session.user.id)}" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">资源名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="required" placeholder="请输入资源名称" value="${(resource.name)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">资源描述：</label>
        <div class="layui-input-block">
            <textarea id="description" name="description" placeholder="请输入内容：必填" lay-verify="required" class="layui-textarea" style="width: 60%" >${(resource.description)}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="type" value="1" title="系统资源" lay-filter="resourcetype" checked="">
            <input type="radio" name="type" value="2" title="外链资源"  lay-filter="resourcetype">
        </div>
    </div>

    <div class="layui-form-item" style="display: none" id="reourcelink">
        <label class="layui-form-label">资源外链：</label>
        <div class="layui-input-inline">
            <input type="text" name="link"  placeholder="请输入外链地址" value="${(resource.link)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" id="uploadreource">
        <label class="layui-form-label">上传资源</label>
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传资源
            </button>
            <input type="hidden" name="id">
        </div>
    </div>

    <div class="layui-form-item" style="margin-bottom: 150px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx!}/js/jquery.js"></script>
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<script src="${ctx!}/js/common.js"></script>

<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base : "${ctx!}/js/"
    }).use(['form','layer','jquery', 'element','upload'], function () {
        var $ = layui.jquery,
                form = layui.form,
                upload = layui.upload,
                layer = layui.layer;

        form.on('radio(resourcetype)', function (data) {
            // alert(data.elem);
            // //console.log(data.elem);
            // alert(data.value);//判断单选框的选中值
            if(data.value === '2'){
                $("#reourcelink").show();
                $("#uploadreource").hide();
            }else{
                $("#reourcelink").hide();
                $("#uploadreource").show();
            }

            //console.log(data.value);
            //alert(data.othis);
            //layer.tips('开关checked：' + (this.checked ? 'true' : 'false'), data.othis);
            //layer.alert('响应点击事件');
        });

        //上传文件设置
        upload.render({
            elem: '#upload' ,//绑定元素
            url: '/upload',
            accept: 'file',
            size:0,
            before: function(input) {
                box = $("#upload").parent('.layui-input-block');
                if (box.next('div').length > 0) {
                    box.next('div').html('<div class="imgbox"><p>上传中...</p></div>');
                } else {
                    box.after('<div class="layui-input-block"><div class="imgbox"><p>上传中...</p></div></div>');
                }
            },
            done: function(res) {
                if (res.isOk) {
                    box.next('div').find('div.imgbox').html('<p>下载地址：<a href="' + res.data.url + '">' + res.data.name + '</a></p>');
                    $('input[type=hidden]').val(res.data.id);
                } else {
                    box.next('div').find('p').html('上传失败...')
                }
            }
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/resource/add",
                data: data.field,
                success: function(ret){
                    if(ret.isOk){
                        layer.msg("添加成功", {time: 2000},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                            window.parent.location.href="/admin/resource/index";
                        });
                    }else{
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>

</html>
