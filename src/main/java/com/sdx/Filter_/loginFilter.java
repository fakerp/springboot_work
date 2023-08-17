package com.sdx.Filter_;

import com.alibaba.fastjson.JSONObject;
import com.sdx.my_class.Result;
import com.sdx.my_function.jwt_tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//设置拦截器作用域
//@WebFilter(urlPatterns = "/*")
@Slf4j
public class loginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //允许进入login页面
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        if (url.contains("login")){
            log.info("登录放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }else
        {
//            获取到请求头里面的token
            String token = request.getHeader("token");
            if (!StringUtils.hasLength(token)){
                String erro = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
                response.getWriter().write(erro);
                return;
            }else {
                try {
                    jwt_tool.returnjwt(token);
                }catch (Exception e){
                    String erro = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
                    response.getWriter().write(erro);
                    return;
                }

                filterChain.doFilter(servletRequest,servletResponse);


            }
        }

    }
}
