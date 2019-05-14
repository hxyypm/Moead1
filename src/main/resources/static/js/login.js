layui.use(['jquery','form'], function () {
    var $ = layui.jquery;
    var form = layui.form;

    //监听登陆
    form.on('submit(loginForm)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'GET',
    		data: data,
    		async:true,
    		url: "/login",
    		success:function(result) {
    			if (result.isOk) {
    				MyLocalStorage.put("user", JSON.stringify(result.data), 360*24*3);
                    window.location.href="/user/index";
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
    //监听注册
    form.on('submit(regForm)', function(data){
    	data = data.field;
    	if (data.password.length<3 || data.password.length>18) {
    		layer.msg("密码必须6到18个字符",{anim:6});
    		return false;
    	}
    	if (data.password!=data.rePassword) {
    		layer.msg("两次密码输入不一致",{anim:6});
    		return false;
    	}
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: "/register",
    		success:function(result) {
    			if (result.isOk) {
    				MyLocalStorage.put("user", JSON.stringify(result.data), 360*24*3);
    				window.location = '/';
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
    
    /*忘记密码*/
    $("#forgetPwd").click(function(){
    	$("#forgetPwdTit").css('display','');
    	$("#forgetPwdTit").click();
    });
    //监听提交忘记密码
    form.on('submit(resetPwd)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: "/resetPwd/send",
    		success:function(result) {
    			if (result.isOk) {
    				layer.alert('已将密码重置地址发至您的邮箱,请注意查收', {icon: 1,anim: 1});
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
});

