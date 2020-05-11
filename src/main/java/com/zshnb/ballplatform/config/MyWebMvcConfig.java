// package com.zshnb.ballplatform.config;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// /**
//  * CreateDate：2020/5/6 <br/>
//  * Author：WangHao <br/>
//  * Description:
//  **/
// @Configuration
// public class MyWebMvcConfig implements WebMvcConfigurer {
//
//     @Value("${spring.file.upload-dir}")
//     private String fileUploadDir;
//
//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.addResourceHandler("/upload/**").addResourceLocations(fileUploadDir);
//         System.out.println();
//     }
// }