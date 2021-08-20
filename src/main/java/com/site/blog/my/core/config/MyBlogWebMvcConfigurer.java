package com.site.blog.my.core.config;

import com.site.blog.my.core.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        //添加一个拦截器，拦截以/admin为前缀的url路径
        //springboot 2.x的如果自定义HandlerInterceptor拦截器时访问静态资源就会被同步拦截，导致页面中访问这些静态资源出现404
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
    }
    // 自定义静态资源映射目录(不会覆盖默认静态资源映射目录)
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResoureHandler：指的是对外暴露的访问路径
        //addResourceLocations：指的是内部文件放置的目录
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/a/b/c").setViewName("blog/amaze/test");
    }
}
