package com.zhuhang.ddmc.common.exception;

import com.zhuhang.ddmc.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException{

    Integer code;
    public ServiceException(String message,Integer code){
        super(message);
        this.code=code;
    }

    public ServiceException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code= resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GlobalException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
