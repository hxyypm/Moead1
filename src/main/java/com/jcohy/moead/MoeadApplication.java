package com.jcohy.moead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class MoeadApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MoeadApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //管理员主页，从登录
//        registry.addViewController("/admin").setViewName("/login");
        //管理后台主页，从登录
        registry.addViewController("/admin/main").setViewName("/admin/main");
        registry.addViewController("/resource/main").setViewName("/resource/main");

        registry.addViewController("/admin/resource/index").setViewName("/resource/index");
        registry.addViewController("/admin/user/index").setViewName("/admin/user/index");
        registry.addViewController("/comment/index").setViewName("/comment/index");



        //前端
        registry.addViewController("/").setViewName("front/index");
        registry.addViewController("/user/login").setViewName("front/login");
        registry.addViewController("/resource/index").setViewName("front/resource");
        registry.addViewController("/user/index").setViewName("front/user");
        registry.addViewController("/question/index").setViewName("front/question");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("102400KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
