﻿layui.use(['element', 'layer', 'util', 'form'], function () {
    var $ = layui.jquery;

    //子栏目导航点击事件
    $('.child-nav span').click(function () {
        $(this).addClass('child-nav-btn-this').siblings().removeClass('child-nav-btn-this');
    });

    //侧边导航开关点击事件
    $('.blog-navicon').click(function () {
        var sear = new RegExp('layui-hide');
        if (sear.test($('.blog-nav-left').attr('class'))) {
            leftIn();
        } else {
            leftOut();
        }
    });
    //侧边导航遮罩点击事件
    $('.blog-mask').click(function () {
        leftOut();
    });
    //blog-body和blog-footer点击事件，用来关闭百度分享和类别导航
    $('.blog-body,.blog-footer').click(function () {
        categoryOut();
    });
    //类别导航开关点击事件
    $('.category-toggle').click(function (e) {
        e.stopPropagation();    //阻止事件冒泡
        categroyIn();
    });
    //类别导航点击事件，用来关闭类别导航
    $('.article-category').click(function () {
        categoryOut();
    });
    //具体类别点击事件
    $('.article-category > a').click(function (e) {
        e.stopPropagation(); //阻止事件冒泡
    });

    //显示侧边导航
    function leftIn() {
        $('.blog-mask').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
        $('.blog-nav-left').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

        $('.blog-mask').removeClass('maskOut');
        $('.blog-mask').addClass('maskIn');
        $('.blog-mask').removeClass('layui-hide');
        $('.blog-mask').addClass('layui-show');

        $('.blog-nav-left').removeClass('leftOut');
        $('.blog-nav-left').addClass('leftIn');
        $('.blog-nav-left').removeClass('layui-hide');
        $('.blog-nav-left').addClass('layui-show');
    }
    //隐藏侧边导航
    function leftOut() {
        $('.blog-mask').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.blog-mask').addClass('layui-hide');
        });
        $('.blog-nav-left').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.blog-nav-left').addClass('layui-hide');
        });

        $('.blog-mask').removeClass('maskIn');
        $('.blog-mask').addClass('maskOut');
        $('.blog-mask').removeClass('layui-show');

        $('.blog-nav-left').removeClass('leftIn');
        $('.blog-nav-left').addClass('leftOut');
        $('.blog-nav-left').removeClass('layui-show');
    }
    //显示类别导航
    function categroyIn() {
        $('.category-toggle').addClass('layui-hide');
        $('.article-category').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

        $('.article-category').removeClass('categoryOut');
        $('.article-category').addClass('categoryIn');
        $('.article-category').addClass('layui-show');
    }
    //隐藏类别导航
    function categoryOut() {
        $('.article-category').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.article-category').removeClass('layui-show');
            $('.category-toggle').removeClass('layui-hide');
        });

        $('.article-category').removeClass('categoryIn');
        $('.article-category').addClass('categoryOut');
    }
    
    $(function(){
    	isUser();
    	//配置背景粒子
	    var config = {
	        vx: 4,	//小球x轴速度,正为右，负为左
	        vy: 4,	//小球y轴速度
	        height: 2,	//小球高宽，其实为正方形，所以不宜太大
	        width: 2,
	        count: 200,		//点个数
	        color: "121, 162, 185", 	//点颜色
	        stroke: "130,255,255", 		//线条颜色
	        dist: 6000, 	//点吸附距离
	        e_dist: 20000, 	//鼠标吸附加速距离
	        max_conn: 10 	//点到点最大连接数
	    };
	    //调用
	    CanvasParticle(config);
    });
    
    
    // 退出
    $(".user-out").click(function(){
    	MyLocalStorage.remove("user");
    	isUser();
    	var url = window.location.href;
        window.location.href="/user/login"
    });
    
    // 判断用户是否登陆
    function isUser() {
    	var user = MyLocalStorage.get("user");
    	if (user!=null) {
    		user = JSON.parse(user);
    		$(".blog-user").empty();
    		$(".blog-user").append('<a href="/"><img src="/images/jcohy.png" alt="" title="" /></a>'+
            	'<a class="user-out"><i class="fa fa-sign-out"></i></a>');
    		if(user.role === 2){
                $(".blog-user").append('<a href="/resource/main">进入后台管理</a>');
            }
            if(user.role === 0 ){
                $(".blog-user").append('<a href="/admin/main">进入后台管理</a>');
            }
    	} else {
    		$(".blog-user").empty();
    		$(".blog-user").append('<a href="/user/login"><i class="fa fa-user-circle-o"></i></a>'+
                	'<a href="/user/login">登陆</a>'+
                	'<a href="/user/register">注册</a>');
    	}
    }
    
    
    
});

//定时 缓存  
var MyLocalStorage = {                  
	/** 
	 * 总容量5M 
	 * 存入缓存，支持字符串类型、json对象的存储 
	 * 页面关闭后依然有效 ie7+都有效 
	 * @param key 缓存key 
	 * @param stringVal 
	 * @time 数字 缓存有效时间（秒） 默认60s  
	 * 注：localStorage 方法存储的数据没有时间限制。第二天、第二周或下一年之后，数据依然可用。不能控制缓存时间，故此扩展 
	 * */  
    put : function(key,stringVal,time){  
        try{  
            if(!localStorage){return false;}  
            if(!time || isNaN(time)){time=60;}  
            var cacheExpireDate = (new Date()-1)+time*1000;  
            var cacheVal = {val:stringVal,exp:cacheExpireDate};  
            localStorage.setItem(key,JSON.stringify(cacheVal));//存入缓存值  
        }catch(e){}   
    }, /**获取缓存*/  
    get : function (key){  
        try{  
            if(!localStorage){return false;}  
            var cacheVal = localStorage.getItem(key);  
            var result = JSON.parse(cacheVal);  
            var now = new Date()-1;  
            if(!result){return null;}//缓存不存在  
            if(now>result.exp){//缓存过期  
                this.remove(key);                     
                return "";  
            }  
            return result.val;  
        }catch(e){  
            this.remove(key);  
            return null;  
        }  
    },/**移除缓存，一般情况不手动调用，缓存过期自动调用*/  
    remove : function(key){  
        if(!localStorage){return false;}  
        localStorage.removeItem(key);  
    },/**清空所有缓存*/  
    clear : function(){  
        if(!localStorage){return false;}  
        localStorage.clear();  
    }
};

/*根据时间字符串获取date对象*/
function formatDate(str) {
	var strs = str.split(" ");
	var ymd = strs[0].split("-");
	var hms = strs[1].split(":");
	return new Date(ymd[0],ymd[1]-1||0,ymd[2]||0,hms[0]||0,hms[1]||0,hms[2]||0);
}