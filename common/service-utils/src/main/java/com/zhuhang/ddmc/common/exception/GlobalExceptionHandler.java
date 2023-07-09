package com.zhuhang.ddmc.common.exception;

import com.zhuhang.ddmc.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     */

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public Result error(ServiceException e){
        return Result.build(null, e.getCode(), e.getMessage());
    }

    /**
     * 统一系统异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }
}
