package com.sdx.Aop_;

import com.alibaba.fastjson.JSONObject;
import com.sdx.Mapper.OperateLogMapper;
import com.sdx.my_class.OperateLog;
import com.sdx.my_function.jwt_tool;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAop {
    @Autowired
    OperateLogMapper operateLogMapper;
    @Autowired
    HttpServletRequest request;

    @Around("@annotation(com.sdx.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//      生成log所需要的信息
        String token = request.getHeader("token");
//        Claims其实相当于一个mapper类型
        Claims returnjwt = jwt_tool.returnjwt(token);
//        获得id
        Integer id = (Integer) returnjwt.get("id");
//        获得操作时间
        LocalDateTime now = LocalDateTime.now();
//        获得操作类名
        String classname = proceedingJoinPoint.getTarget().getClass().getName();
//        获得操作方法名
        String methodname = proceedingJoinPoint.getSignature().getName();
//        获得操作参数
        Object[] args = proceedingJoinPoint.getArgs();
        String sarg = Arrays.toString(args);
//        获得方法返回值
        long begin = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        String returnvalue = JSONObject.toJSONString(proceed);
//        获得操作耗时
        long timeing = end - begin;


//        执行操作后自动生成一条日志记录
        operateLogMapper.insert(new OperateLog(null,id,now,classname,methodname,sarg,returnvalue,timeing));



        return proceed;
    }
}
