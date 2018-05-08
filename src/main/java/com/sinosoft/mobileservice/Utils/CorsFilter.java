package com.sinosoft.mobileservice.Utils;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 解决跨域问题*/
public class CorsFilter implements Filter {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);

    final static String ACCESS_CONTROL_HEADERS="DNT,X-Mx-ReqToken,Keep-Alive,User-Agent," +
            "X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,SessionToken";

    //自定义头，添加允许协议 否则头不允许被使用
    final static String CUSTOM_ACCESS_CONTROL_HEADERS="x-my-header";

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");//ajax请求过程中需要附带cookie则将* 改为 null
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", ACCESS_CONTROL_HEADERS+","+CUSTOM_ACCESS_CONTROL_HEADERS);
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许跨域请求中携带cookie

        String my=response.getHeader("x-my-header");
            System.out.println(my);
//        System.out.println("*********************************过滤器被使用**************************");
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}