package com.sdx.Exception_;

import com.sdx.my_class.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常控制器，用@RestControllerAdvice来声名
@RestControllerAdvice
public class GlobalHandler {
//    ExceptionHandler表示要处理的异常类型
    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e){
        e.printStackTrace();
        return Result.error("操作异常，请联系管理员");
    }
}
