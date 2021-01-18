package com.example.initial_frame.common.restful;

import lombok.Data;

@Data
public class ResponseData {

    private  ResponseCode code;

    private String message;

    private Object data;

    private ResponseData(ResponseCode code, String message, Object data){
        this.code = code;
        this.message =message;
        this.data =data;
    }

    public static ResponseData SUCCESS (Object o){
        return new ResponseData(ResponseCode.SUCCESS,ResponseCode.SUCCESS.getMessage(),o);
    }
    public static ResponseData SUCCESS (String massage,Object o){
        return new ResponseData(ResponseCode.SUCCESS,massage,o);
    }
    public static ResponseData FAILED (Object o){
        return new ResponseData(ResponseCode.FAILED,ResponseCode.FAILED.getMessage(),o);
    }
    public static ResponseData FAILED (String massage,Object o){
        return new ResponseData(ResponseCode.FAILED,massage,o);
    }
    public static ResponseData FREE (ResponseCode code,Object o){
        return new ResponseData(code,code.getMessage(),o);
    }
}
