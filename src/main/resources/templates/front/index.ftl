<!DOCTYPE html>
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
				<li class="layui-nav-item layui-this">
					<a href="/"><i class="fa fa-home fa-fw"></i>&nbsp;算法简介</a>
				</li>

                <li class="layui-nav-item">
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
                <div class="layui-tab layui-tab-brief">
                    <div class="layui-tab-content">
<#--                        <div class="layui-tab-item layui-show">-->
<#--                            <div id="content"></div>-->
<#--                        </div>-->
                        <div id="test-editormd">
                            　　<textarea style="display:none;" placeholder="markdown语言"><@algorithmic> ${algorithmic.content}</@algorithmic></textarea>
                        </div>
                    </div>
                </div>
                <!--右边小栏目-->
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
<#--    <script src="${ctx!}/js/marked.js"></script>-->
    <script src="${ctx!}/js/jquery.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/marked.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/prettify.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/raphael.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/underscore.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/sequence-diagram.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/flowchart.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/lib/jquery.flowchart.min.js"></script>
    <script src="${ctx!}/js/plugins/editor/editormd.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/css/editormd.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx!}/js/plugins/editor/lib/codemirror/codemirror.min.css"/>

    <script type="text/javascript">

        layui.define([ 'layer','form'], function (exports) {
            var $ = layui.jquery,
                    form  = layui.form ;
            $.ajax({
                type: "GET",
                async:true,
                url: "/algorithmic/info",
                success:function(result) {
                    if (result.isOk) {
                        // document.getElementById('test-editormd').innerHTML =result.data.content

                        editormd.markdownToHTML("test-editormd", {
                            htmlDecode      : "style,script,iframe",
                            emoji           : true,
                            taskList        : true,
                            tex             : true,  // 默认不解析
                            flowChart       : true,  // 默认不解析
                            sequenceDiagram : true  // 默认不解析
                        });
                    } else {
                        layer.msg(result.msg,{anim:6});
                    }
                }
            });
        });
    </script>
</body>
</html>