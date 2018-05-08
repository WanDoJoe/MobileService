package com.sinosoft.mobileservice;

/**
 * Spring boot 启动基类  必须放在依赖注入类的最上层
 */

import com.github.pagehelper.PageHelper;
import com.sinosoft.mobileservice.Utils.CorsFilter;
import com.sinosoft.mobileservice.jersey.JerseyConfig;
import org.apache.ibatis.plugin.Interceptor;
//import org.glassfish.jersey.servlet.ServletContainer;
//import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(value = "com.sinosoft.mobileservice.mybatis")
@ComponentScan
//        (value = { "com.sinosoft.mobileservice.mybatis.mapperInterface","com.sinosoft.mobileservice.controller", "com.sinosoft.mobileservice.dao.impl",
//        "com.sinosoft.mobileservice.services.impl","com.sinosoft.mobileservice.shiro"})
public class MobileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileServiceApplication.class, args);
    }

//    @SuppressWarnings("rawtypes")
//    @Bean
//    public ServletRegistrationBean jersetServlet() {
//        @SuppressWarnings("unchecked")
//        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
//        // our rest resources will be available in the path /jersey/*
//        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
//        return registration;
//    }




    /**
     * 跨域过滤器
     * @return
     */
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
////        return new CorsFilter(source);
//        return new CorsFilter();
//    }
//
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        return corsConfiguration;
//    }

    /**
     *  //分页插件
     * @return
     */
    @Bean
    PageHelper pageHelper(){

        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});
        return pageHelper;
    }
}
