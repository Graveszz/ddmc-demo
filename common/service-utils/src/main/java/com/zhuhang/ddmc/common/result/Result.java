package com.zhuhang.ddmc.common.result;

import lombok.Data;

/**
 * 统一返回结果类
 * @param <T>
 */
@Data
public class Result<T> {

    Integer code;

    String message;

    T data;

    private Result(){

    }

    /**
     * 静态方法中的泛型必须声明，因为未实例化不确定类型
     * @param data
     * @param codeEnum
     * @return
     * @param <T>
     */
    public static <T> Result<T> build(T data,ResultCodeEnum codeEnum){

        Result<T> result = new Result<>();

        if (data!=null){
            result.setData(data);
        }

        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getMessage());

        return result;

    }
    public static <T> Result<T> build(T data,Integer code,String message){

        Result<T> result = new Result<>();

        if (data!=null){
            result.setData(data);
        }

        result.setCode(code);
        result.setMessage(message);

        return result;

    }

    public static<T> Result<T> success(T data){
        Result<T> result = new Result<>();

        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> success(){
        Result<T> result = new Result<>();

        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();

        return build(data, ResultCodeEnum.FAIL);
    }

    public static<T> Result<T> fail(){
        Result<T> result = new Result<>();

        return build(null, ResultCodeEnum.FAIL);
    }

}
