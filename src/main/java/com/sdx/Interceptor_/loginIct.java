package com.sdx.Interceptor_;

import com.alibaba.fastjson.JSONObject;
import com.sdx.my_class.Result;
import com.sdx.my_function.jwt_tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
//作用在拦截器之后，只作用于spring里面的资源
public class loginIct implements HandlerInterceptor {
    //    资源发生前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        if (url.contains("login")) {
            log.info("登录放行");
            return true;
        } else {
//            获取到请求头里面的token
            String token = request.getHeader("token");
            if (!StringUtils.hasLength(token)) {
                String erro = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
                response.getWriter().write(erro);
                return false;
            } else {
                try {
                    jwt_tool.returnjwt(token);
                } catch (Exception e) {
                    String erro = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
                    response.getWriter().write(erro);
                    return false;
                }

                return true;
            }
        }

    }
}


