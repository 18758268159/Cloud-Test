package com.ethan.server.zuul.util;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * ZuulFilter 过滤服务
 */
@Component  // 此注解表示将MyFilter 类交给Spring管理
public class MyFilter  extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {

        // http://localhost:2003/api-web-mvc/getWebMvcApi?token=1111

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        // 判断请求中是否-携带token 如果有 则过 没有则返回 "token is empty"
        Object accessToken = request.getParameter("token"); // 获取请求当中的token参数

//        String refer =  request.getHeader("referrer");
//        log.info( "refer >>> %s " , refer);

//        if (accessToken != null) {
//            return null;
//        }
//        log.warn("token is empty");
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(401);
//        try {
//            ctx.getResponse().getWriter().write("token is empty");
//        } catch (Exception e) {
//        }
        return null;

    }

}
