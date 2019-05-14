<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>-个人中心</title>
    <link rel="shortcut icon" href="${ctx!}/images/logo.png" type="image/x-icon">
    <!--Layui-->
    <link href="${ctx!}/layui/css/layui.css" rel="stylesheet" />
    <!--font-awesome-->
    <link href="${ctx!}/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${ctx!}/css/global.css" rel="stylesheet" />
    <!-- 本页样式表 -->
    <link href="${ctx!}/css/question.css" rel="stylesheet" />

</head>
<body>
<!-- 导航 -->
<nav class="blog-nav layui-header">
    <div class="blog-container">
        <!-- 用户登陆 -->
        <div class="blog-user"></div>
        <a class="blog-logo" href="/">Moea教学管理系统</a>
        <!-- 导航菜单 -->
        <ul class="layui-nav" lay-filter="nav">
            <li class="layui-nav-item">
                <a href="/"><i class="fa fa-home fa-fw"></i>&nbsp;算法简介</a>
            </li>

            <li class="layui-nav-item ">
                <a href="/resource/index"><i class="fa fa-home fa-fw"></i>&nbsp;资源分享</a>
            </li>

            <li class="layui-nav-item  layui-this">
                <a href="/question/index"><i class="fa fa-home fa-fw"></i>&nbsp;在线留言</a>
            </li>

            <li class="layui-nav-item ">
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
    <div class="blog-container">
        <input id="token" type="hidden">
        <input id="uid" type="hidden">
        <blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow">
            <a href="/" title="网站首页">网站首页</a>
            <a><cite>留言板</cite></a>
        </blockquote>
        <div class="blog-main">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <div class="layui-tab-content">
                    <!-- 留言墙 -->
                    <div class="layui-item">
                        <div class="aboutinfo">
                            <div class="aboutinfo-figure">
                                <img src="${ctx!}/images/messagewall.png" alt="留言墙" />
                            </div>
                            <p class="aboutinfo-nickname">留言墙</p>
                            <p class="aboutinfo-introduce">本页面可留言、吐槽、提问。欢迎灌水，杜绝广告！</p>
                            <p class="aboutinfo-location">
                                <i class="fa fa-clock-o"></i>&nbsp;<span id="time"></span>
                            </p>
                            <hr />
                            <div class="aboutinfo-contact">
                                <p style="font-size:2em;">沟通交流，拉近你我！</p>
                            </div>
                            <fieldset class="layui-elem-field layui-field-title">
                                <legend>Leave a message</legend>
                                <div class="layui-field-box">
                                    <div class="leavemessage" style="text-align:initial">
                                        <form class="layui-form blog-editor" action="">
                                            <div class="layui-form-item">
                                                <textarea name="editorContent" lay-verify="content" id="remarkEditor" placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                            </div>
                                            <div class="layui-form-item">
                                                <button class="layui-btn" lay-submit="formLeaveMessage" lay-filter="formLeaveMessage">提交问题</button>
                                            </div>
                                        </form>
                                        <ul class="blog-comment">
                                        </ul>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div><!--留言墙End-->

                </div>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<footer class="blog-footer">
    <p><span>Copyright</span><span>&copy;</span><span>2019</span><a href="/">Moead</a></p>
</footer>


<!--遮罩-->
<div class="blog-mask animated layui-hide"></div>
<!-- layui.js -->
<script src="${ctx!}/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="${ctx!}/js/global.js"></script>
<script src="${ctx!}/js/canvas-particle.js"></script>
<!-- 本页脚本 -->
<script src="${ctx!}/js/question.js"></script>

</body>
</html>