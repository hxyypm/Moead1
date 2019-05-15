﻿<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>Moea教学管理系统</title>
    <link rel="shortcut icon" href="${ctx!}/images/logo.png" type="image/x-icon">
    <!--Layui-->
    <link href="${ctx!}/layui/css/layui.css" rel="stylesheet" />
    <!--font-awesome-->
    <link href="${ctx!}/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${ctx!}/css/global.css" rel="stylesheet" />
    <link href="${ctx!}/css/animate.min.css" rel="stylesheet" />
    <!-- 本页样式表 -->
    <link href="${ctx!}/css/index.css" rel="stylesheet" />
</head>
<body>
<!-- 导航 -->
<nav class="blog-nav layui-header">
    <div class="blog-container">
        <!-- 用户登陆 -->

        <a class="blog-logo" href="/">Moea教学管理系统</a>
        <!-- 导航菜单 -->
        <div class="blog-user"></div>

        <ul class="layui-nav" lay-filter="nav">
            <li class="layui-nav-item">
                <a href="/"><i class="fa fa-home fa-fw"></i>&nbsp;算法简介</a>
            </li>

            <li class="layui-nav-item  layui-this">
                <a href="/resource/index"><i class="fa fa-home fa-fw"></i>&nbsp;资源分享</a>
            </li>

            <li class="layui-nav-item">
                <a href="/question/index"><i class="fa fa-home fa-fw"></i>&nbsp;在线留言</a>
            </li>

            <li class="layui-nav-item">
                <a href="/user/index"><i class="fa fa-tags fa-fw"></i>&nbsp;个人中心</a>
            </li>
        </ul>
        <!-- 手机和平板的导航开关 -->
        <a class="blog-navicon" href="javascript:;">
            <i class="fa fa-navicon"></i>
        </a>
    </div>
</nav>
<!-- 主体（一般只改变这里的内容） -->
<div class="blog-body">
    <div class="layui-carousel blog-bg" id="carousel">
        <div carousel-item>
            <div class="bg bg_a"></div>
            <div class="bg bg_b"></div>
            <div class="bg bg_c"></div>
            <div class="bg bg_d"></div>
            <div class="bg bg_e"></div>
        </div>
    </div>
    <div class="blog-container">
        <div class="blog-main">
            <!--左边文章列表-->
            <div class="blog-main-left animated slideInLeft" id="jobList">
                <@resourceList>
                <#list list as x>
                    <div class="article shadow animated fadeInLeft">
                        <div class="article-left ">
                            <img src="${ctx!}/images/01.jpg" alt="${x.name}"/>
                        </div>

                        <div class="article-right">
                            <div class="article-title">
                                资源名称：${x.name}
                            </div>
                            <div class="article-abstract">
                                描述：${x.description}
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;${x.time}</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;${x.user.name}</span>
                            <#if x.url != null >
                                <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="${x.url}">${x.url}</a></span>
                            <#else >
                                <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a target="_blank"    href="${x.link}">${x.link}</a></span>
                            </#if>
                        </div>
                    </div>
                </#list>
            </@resourceList>
        </div>
        <!--右边小栏目-->
        <div class="blog-main-right">
            <div class="blog-search">
                <form class="layui-form" action="">
                    <div class="layui-form-item">
                        <div class="search-keywords  shadow">
                            <input type="text" name="key" lay-verify="required" placeholder="请输入资源名称" autocomplete="off" class="layui-input ">
                        </div>
                        <div class="search-submit  shadow">
                            <a class="search-btn" lay-submit lay-filter="formSearch"><i class="fa fa-search"></i></a>
                        </div>
                    </div>
                </form>
            </div>
            <!--推荐/点击排行-->
            <#--<div class="layui-tab layui-tab-brief shadow animated fadeInRight" lay-filter="docDemoTabBrief">-->
                <#--<ul class="layui-tab-title">-->
                    <#--<li class="layui-this">点击排行</li>-->
                    <#--<!--<li>站长推荐</li>&ndash;&gt;-->
                <#--</ul>-->
                <#--<div class="layui-tab-content">-->
                    <#--<div class="layui-tab-item layui-show">-->
                        <#--<ul class="blog-module-ul">-->
                            <#--<@resourceList>-->
                            <#--<#list list as x>-->
                                <#--<#if x_index == 0>-->
                                    <#--<li><i class="layui-badge-rim layui-bg-red">${x_index+1}</i>&nbsp;&nbsp;<a href="${x.url}/${x.id}">${x.name}</a></li>-->
                                    <#--<#elseif x_index == 1>-->
                                        <#--<li><i class="layui-badge-rim layui-bg-orange">${x_index+1}</i>&nbsp;&nbsp;<a href="${x.url}/${x.id}">${x.name}</a></li>-->
                                        <#--<#elseif x_index == 2>-->
                                            <#--<li><i class="layui-badge-rim layui-bg-green">${x_index+1}</i>&nbsp;&nbsp;<a href="${x.url}/${x.id}">${x.name}</a></li>-->
                                            <#--<#else >-->
                                                <#--<li><i class="layui-badge-rim">${x_index+1}</i>&nbsp;&nbsp;<a href="${x.url}/${x.id}">${x.name}</a></li>-->
                                <#--</#if>-->

                            <#--</#list>-->
                        <#--</@resourceList>-->
                        <#--</ul>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div class="blog-module shadow">-->
                <#--<div class="blog-module-title">友情链接</div>-->
                <#--<ul class="blogroll">-->
                    <#--<li><a target="_blank" href="http://www.layui.com/" title="Layui">Layui</a></li>-->
                    <#--<li><a target="_blank" href="http://www.pagemark.cn/" title="页签">页签</a></li>-->
                <#--</ul>-->
            <#--</div>-->
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
<!-- 底部 -->
<footer class="blog-footer">
    <p><span>Copyright</span><span>&copy;</span><span>2019</span><a href="/">Moead</a></p>
</footer>
<!--侧边导航-->
<ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
    <li class="layui-nav-item layui-this">
        <a href="/"><i class="fa fa-home fa-fw"></i>&nbsp;招聘信息</a>
    </li>

    <li class="layui-nav-item">
        <a href="/user/index"><i class="fa fa-tags fa-fw"></i>&nbsp;个人中心</a>
    </li>
</ul>

<!--遮罩-->
<div class="blog-mask animated layui-hide"></div>
<!-- layui.js -->
<script src="${ctx!}/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="${ctx!}/js/global.js"></script>
<script src="${ctx!}/js/canvas-particle.js"></script>
<!-- 本页脚本 -->
<script src="${ctx!}/js/index.js"></script>
<script type="text/javascript">
    layui.define([ 'layer','form'], function (exports) {
        var $ = layui.jquery,
            form  = layui.form ;
       //监听搜索提交
       form.on('submit(formSearch)', function (data) {
           $.ajax({
               type: "GET",
               dataType: "json",
               data: data.field,
               url: "/resource/search",
               success:function(result) {
                   if (result.isOk) {
                       console.log(result.data);
                       createHtml(result.data);
                   } else {
                       layer.msg(result.msg,{anim:6});
                   }
               }
           });
           return false;
       });
//
       function createHtml(data) {
           obj = $("#jobList");
           obj.empty();
           var detailHtml = '';
           for(var i=0;i<data.length;i++){
               detailHtml += '<div class="article shadow animated fadeInLeft">';
               detailHtml += '<div class="article-left ">';
               detailHtml += '<img src="/images/01.jpg" alt="'+data[i].name+'"/>';
               detailHtml += '</div>';
               detailHtml += '<div class="article-right">';
               detailHtml += '<div class="article-title">';
               detailHtml += '资源名称：'+data[i].name+'</a></div>';
               detailHtml += '<div class="article-abstract">+描述：'+data[i].description+'</div>';
               detailHtml += '</div>';
               detailHtml += '<div class="clear"></div>';
               detailHtml += '<div class="article-footer">';
               detailHtml += '<span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;'+data[i].time+'</span>';
               detailHtml += '<span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;'+data[i].user.name+'</span>';
               detailHtml += '<span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">'+data[i].url+'</a></span>';
               detailHtml += '</div></div>';
           }
           obj.append(detailHtml);
       }
    });
</script>
</body>
</html>