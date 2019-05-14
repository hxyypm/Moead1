var $;
layui.use(['jquery','form','util','upload','laydate'], function () {
    $ = layui.jquery;
    var util = layui.util,
     form = layui.form,
	 laydate = layui.laydate,
     upload = layui.upload;

    laydate.render({
        elem: '#resumebirth' //指定元素
    });
    laydate.render({
        elem: '#birth' //指定元素
    });

    var user = MyLocalStorage.get("user");
    if (user!=null && user!='') {
    	user = JSON.parse(user);
    	var sex = 0 ;
    	var resumeSex = 3;
        $("#id").val(user.id);
        $("#user").val(user.id);
    	$("#num").val(user.num);
    	$("#name").val(user.name);
    	$("#phone").val(user.phone);
    	$("#email").val(user.email);
        $("#birth").val(user.birth);

        if(user.sex == "女"){
        	sex = 1;
		}else{
        	sex = 0;
		}
    	$(".layui-form-radio").get(sex).click();
    } else {
    	layer.msg('会话已过期,请先去登陆',{anim:6,time:2000});
    	location.href =  "/user/login";
    }


    // 修改个人资料
    form.on('submit(formInfo)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'GET',
    		data: data,
    		async:true,
    		url: "/update",
    		success:function(result) {
    			if (result.isOk) {
    				MyLocalStorage.put("user", JSON.stringify(result.data), 360*24*3);
    				layer.msg("修改成功!",{icon:1});
                    window.location.href = "/user/index";
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });

    //上传文件设置
    upload.render({
        elem: '#upload' ,//绑定元素
        url: '/upload',
        accept: 'file',
        size:0,
        before: function(input) {
            console.log($(input));
            box = $("#upload").parent('.layui-input-block');
            console.log(box);
            if (box.next('div').length > 0) {
                box.next('div').html('<div class="imgbox"><p>上传中...</p></div>');
            } else {
                box.after('<div class="layui-input-block"><div class="imgbox"><p>上传中...</p></div></div>');
            }
        },
        done: function(res) {
            if (res.isOk) {
                box.next('div').find('div.imgbox').html('<p>下载地址：<a href="' + res.data.downloadUrl + '">' + res.data.fileName + '</a></p>');
                box.find('input[type=hidden]').val(res.book.id);
            } else {
                box.next('div').find('p').html('上传失败...')
            }
        }
    });

	// 修改密码
    form.on('submit(formPwd)', function(data){
    	data = data.field;
    	if (data.newP.length<6 || data.newP.length>18) {
    		layer.msg("密码必须6到18个字符",{anim:6});
    		return false;
    	}
    	if (data.newP!=data.repwd) {
    		layer.msg("两次密码输入不一致",{anim:6});
    		return false;
    	}

    	$.ajax({
    		type: 'get',
    		data: data,
    		async:true,
    		url: "/changePass",
    		success:function(result) {
    			if (result.isOk) {
    				layer.msg("修改成功!",{icon:1});
                    window.location.href = "/user/index";
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
});
