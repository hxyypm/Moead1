layui.use(['element', 'jquery', 'form', 'layedit'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var user = MyLocalStorage.get("user");
    if (user == null || user  ==='') {
        layer.msg('会话已过期,请先去登陆',{anim:6,time:2000});
        location.href =  "/user/login";
    }else{
        user = JSON.parse(user);
    }
    $.ajax({
        type: 'GET',
        async:false,
        url: "/question/list",
        success:function(result) {
            if (result.isOk) {
                var questionList = result.data;
                for(let i = 0; i<questionList.length; i++){
                    var html = '<li>' +
                        '<div class="comment-parent"><img src="../images/jcohy.png"alt=""/>' +
                        '<div class="info"><span class="username">'+questionList[i].username+'</span>'+"("+questionList[i].roleName+")："+'</div>' +
                        '<div class="content">' + questionList[i].description + '</div>' +
                        '<p class="info info-footer"><span class="time">'+questionList[i].time+'</span><a class="btn-reply"href="javascript:;" onclick="btnReplyClick(this)">回复</a></p>' +
                        '</div><!--回复表单默认隐藏-->' +
                        '<div  id="question'+ questionList[i].id +'" class="replycontainer layui-hide">' +
                        '<form class="layui-form"action="">' +
                        '<div class="layui-form-item"><input  type="hidden" name="questionId" value="'+questionList[i].id+'"/></div>' +
                        '<div class="layui-form-item"><textarea name="replyContent"lay-verify="replyContent"placeholder="请输入回复内容"class="layui-textarea"style="min-height:80px;"></textarea></div>' +
                        '<div class="layui-form-item"><button class="layui-btn layui-btn-mini"lay-submit="formReply"lay-filter="formReply">提交</button></div>' +
                        '</form>' +
                        '</div><hr>' +
                        '</li>';
                    $('.blog-comment').append(html);
                    var replies = questionList[i].replies
                    if(replies != null && replies.length >0){
                        for(let i=0;i<replies.length;i++){
                            var questionId = replies[i].questionId;
                            var replyhtml = '<div class="comment-child"><img src="../images/jcohy.png" alt="Jcohy"/>' +
                                '<div class="info"><span class="username">'+replies[i].username+'</span>'+"("+replies[i].roleName+")："+'<span> &nbsp; &nbsp;' + replies[i].content + '</span></div>' +
                                '<p class="info"><span class="time">' + replies[i].time + '</span></p>' +
                                '</div>';

                            $("#question"+questionId).before(replyhtml).siblings('.comment-parent').children('p').children('a').click();
                        }
                    }
                }
            } else {
                layer.msg(result.msg,{anim:6});
            }
        }
    });

    //评论和留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
    });
    //评论和留言的编辑器的验证
    layui.form.verify({
        content: function (value) {
            value = $.trim(layedit.getText(editIndex));
            if (value == "") return "自少得有一个字吧";
            layedit.sync(editIndex);
        }
    });

    //监听留言提交
    form.on('submit(formLeaveMessage)', function (data) {
        var content = data.field.editorContent;
        var index = layer.load(1);

        if(user.role === 1){
            $.ajax({
                type: 'POST',
                data:{"content":content,"userId":user.id},
                async:false,
                url: "/question/add",
                success:function(result) {
                    if (result.isOk) {
                        var question = result.data;
                        var html = '<li>' +
                            '<div class="comment-parent"><img src="../images/jcohy.png"alt=""/>' +
                            '<div class="info"><span class="username">'+question.username+'</span>'+"("+question.roleName+")："+'</div>' +
                            '<div class="content">' + question.description + '</div>' +
                            '<p class="info info-footer"><span class="time">'+question.time+'</span><a class="btn-reply"href="javascript:;" onclick="btnReplyClick(this)">回复</a></p>' +
                            '</div><!--回复表单默认隐藏-->' +
                            '<div  id="question'+ questionList[i].id +'" class="replycontainer layui-hide">' +
                            '<form class="layui-form"action=""><div class="layui-form-item"><textarea name="replyContent"lay-verify="replyContent"placeholder="请输入回复内容"class="layui-textarea"style="min-height:80px;"></textarea></div><div class="layui-form-item"><button class="layui-btn layui-btn-mini"lay-submit="formReply"lay-filter="formReply">提交</button></div></form>' +
                            '</div><hr>' +
                            '</li>';
                        $('.blog-comment').append(html);
                        $('#remarkEditor').val('');
                        editIndex = layui.layedit.build('remarkEditor', {
                            height: 150,
                            tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
                        });
                        layer.msg("留言成功", { icon: 1 });
                        layer.close(index);
                    } else {
                        layer.msg(result.msg,{anim:6});
                    }
                }
            });
        }else{
            layer.msg("只允许学生留言！",{anim:6});
            layer.close(index);
        }

        return false;
    });

    //监听留言回复提交
    form.on('submit(formReply)', function (data) {
        var index = layer.load(1);
        var content = data.field.replyContent;
        var questionId = data.field.questionId;
        //留言回复

        if(user.role === 2){
            $.ajax({
                type: 'POST',
                data:{"content":content,"userId":user.id,"questionId":questionId},
                async:false,
                url: "/reply/add",
                success:function(result) {
                    if (result.isOk) {
                        var reply = result.data;
                        var replyhtml = '<div class="comment-child"><img src="../images/jcohy.png" alt="Jcohy"/>' +
                            '<div class="info"><span class="username">'+reply.username+'</span>'+"("+reply.roleName+")："+'<span> &nbsp; &nbsp;' + reply.content + '</span></div>' +
                            '<p class="info"><span class="time">' + reply.time + '</span></p>' +
                            '</div>';

                        $("#question"+questionId).before(replyhtml).siblings('.comment-parent').children('p').children('a').click();
                        $('#remarkEditor').val('');
                        editIndex = layui.layedit.build('remarkEditor', {
                            height: 150,
                            tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
                        });
                        layer.msg("回复成功", { icon: 1 });
                        layer.close(index);
                    } else {
                        layer.msg(result.msg,{anim:6});
                        layer.close(index);
                    }
                }
            });
        }else{
            layer.msg("只允许老师回复！",{anim:6});
            layer.close(index);
        }


        return false;
    });
});
function btnReplyClick(elem) {
    var $ = layui.jquery;
    $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-hide');
    if ($(elem).text() == '回复') {
        $(elem).text('收起')
    } else {
        $(elem).text('回复')
    }
}
systemTime();

function systemTime() {
    //获取系统时间。
    var dateTime = new Date();
    var year = dateTime.getFullYear();
    var month = dateTime.getMonth() + 1;
    var day = dateTime.getDate();
    var hh = dateTime.getHours();
    var mm = dateTime.getMinutes();
    var ss = dateTime.getSeconds();

    //分秒时间是一位数字，在数字前补0。
    mm = extra(mm);
    ss = extra(ss);

    //将时间显示到ID为time的位置，时间格式形如：19:18:02
    document.getElementById("time").innerHTML = year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
    //每隔1000ms执行方法systemTime()。
    setTimeout("systemTime()", 1000);
}

//补位函数。
function extra(x) {
    //如果传入数字小于10，数字前补一位0。
    if (x < 10) {
        return "0" + x;
    }
    else {
        return x;
    }
}