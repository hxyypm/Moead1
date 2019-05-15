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
        ,url: '/comment/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', align:'center', title: '编号',unresize:true}
            ,{field: 'username', align:'center', title: '用户名',unresize:true,templet: '<div>{{d.username}}</div>'}
            ,{field: 'description', align:'center', title: '描述',unresize:true}
            // ,{field: 'type', align:'center', title: '类型',unresize:true}
            ,{field: 'time', align:'center', title: '创建时间',unresize:true}
            ,{fixed: 'right', title:'操作',align:'center',width:'200',toolbar: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'reply'){
            common.frame_show('编辑','/resource/form?id='+data.id);
        }
    });

    function del(id) {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/comment/del/" + id,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/comment/index";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }
    exports('comment/index');
});