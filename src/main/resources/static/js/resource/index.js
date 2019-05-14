layui.define([ 'layer',  'table','common','util'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        util = layui.util,
        table  = layui.table ;
    table.render({
        elem: '#reource'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/resource/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'name', align:'center', title: '名称',unresize:true}
            ,{field: 'username', align:'center', title: '用户名',unresize:true,templet: '<div>{{d.user.name}}</div>'}
            ,{field: 'description', align:'center', title: '描述',unresize:true}
            ,{field: 'url', align:'center', title: '下载地址',unresize:true}
            ,{field: 'uploadUrl', align:'center', title: '上传地址',unresize:true}
            // ,{field: 'type', align:'center', title: '类型',unresize:true}
            ,{field: 'time', align:'center', title: '创建时间',unresize:true,templet: '<div>{{# if(d.time!=null){ }}{{ layui.util.toDateString(d.time) }}{{# } }}</div>'}
            ,{fixed: 'right', title:'操作',align:'center',width:'200',toolbar: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'edit'){
            common.frame_show('编辑','/resource/form?id='+data.id);
        }
    });


    //输出接口，主要是两个函数，一个删除一个编辑
    var datalist = {
        deleteData: function (id) {
            layer.confirm('确定删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                del(id);
            }, function () {
            });
        },
        editData: function (id) {
            common.frame_show('编辑','/resource/form?id='+id);
        }
    };

    //添加数据
    $('#addResource').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            common.frame_show('添加','/resource/form');
    }, 500);
    });

    function del(id) {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/resource/del/" + id,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/admin/resource/index";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }
    exports('resource/index', datalist);
});