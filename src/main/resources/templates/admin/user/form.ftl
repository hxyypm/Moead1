<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 添加用户</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/css/editormd.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/lib/codemirror/codemirror.min.css" />
    <style type="text/css">
        .layui-form-item{
            margin: 50px 0 0 200px
        }
    </style>
</head>

<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">添加用户</legend>
</fieldset>
<form class="layui-form layui-form-pane"  lay-filter="form">


    <div class="layui-form-item" style="visibility: hidden">
        <div class="layui-form-item">
            <input type="hidden" name="userId"  value="${(Session.user.id)}" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-inline">
            <input type="text" name="num" lay-verify="number" placeholder="请输入用户编号" value="${(users.num)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="required" placeholder="请输入用户名" value="${(users.name)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系方法</label>
        <div class="layui-input-inline">
            <input type="text" name="phone" lay-verify="required" placeholder="请输入联系方法" value="${(users.phone)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">Email</label>
        <div class="layui-input-inline">
            <input type="text" name="email" lay-verify="required" placeholder="请输入Email" value="${(users.email)}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">出生日期</label>
        <div class="layui-input-inline">
            <input id="birth" type="text" name="birth" autocomplete="off" class="layui-input" value="${(users.birth)}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户身份</label>
        <div class="layui-input-block" style="margin-left: 0">
            <select name="role" lay-verify="">
                <option value="">请选择一个角色</option>
                <option value="1">学生</option>
                <option value="2">老师</option>
                <option value="0">管理员</option>
            </select>
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
    }).use(['form','layer','jquery', 'element','laydate'], function () {
        var $ = layui.jquery,
                form = layui.form,
                laydate = layui.laydate,
                layer = layui.layer;
        laydate.render({
            elem: '#birth' //指定元素
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/user/add",
                data: data.field,
                success: function(ret){
                    if(ret.isOk){
                        layer.msg("添加成功", {time: 2000},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                            window.parent.location.href="/admin/user/index";
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
