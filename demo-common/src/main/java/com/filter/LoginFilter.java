package com.filter;

import com.exception.ServiceException;
import com.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@WebFilter(urlPatterns = "/", filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Autowired
    RedisUtil redisUtil;
    @Value("${USER_SESSION_EXPIRE}")
    private Integer USER_SESSION_EXPIRE;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoginFilter init");
    }
    //直接放行的请求
    public final static List<String> chainListUrls =
            new ArrayList<String>(Arrays.asList("/admin/login",
                                            "/baseNews/getAllNews",
                                            "/base/baseList",
                                            "/baseNews/details",
                                            "/base/details",
                                            "/leaveMsg/getMsg",
                                            "/admin/register",
                                            "/admin/checkAccount",
                                            "/admin/sendValidCode"));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("LoginFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String paramNo = request.getHeader("userNo");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //当前请求属于直接放行
        if (chainListUrls.contains(request.getRequestURI())) {
            //设置跨域
            allowCrossOrigin(request, response);
            //请求放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String userJson = "";
        try {
            userJson = String.valueOf(redisUtil.get(paramNo));
        } catch (RedisSystemException e) {
            e.printStackTrace();
            throw new ServiceException("Redis访问超时,请重新操作");
        }
        //如果当前登录信息不存在
        if (StringUtils.isBlank(userJson) || StringUtils.equals("null",userJson)) {
            throw new ServiceException("登录失效,请重新登录");
        }
        //有操作的，更新过期时间
        redisUtil.expire(paramNo,USER_SESSION_EXPIRE);
        //设置跨域
        allowCrossOrigin(request, response);
        //请求放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("LoginFilter destroy");
    }


    /**
     * cors support
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 是否支持安全证书
        config.addAllowedOrigin("*"); // 允许任何域名使用
        config.addAllowedHeader("*"); // 允许任何头
        config.addAllowedMethod("*"); // 允许任何方法（post、get等）
        // 预检请求的有效期，单位为秒。
        //        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }



    /**
     * 设置允许跨域
     * @param response
     */
    private void allowCrossOrigin(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type, userNo");
//
//        response.setHeader("Pragma","No-cache");
//        response.setHeader("Cache-Control","no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
//        response.setHeader("Access-Control-Allow-Headers", "accept, content-type, userNo");
    }
}
