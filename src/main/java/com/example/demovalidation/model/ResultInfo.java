package com.example.demovalidation.model;

import lombok.Data;

@Data
public class ResultInfo<T> {
    private Integer status;
    private String message;
    private T response;

    public ResultInfo<T> success(Integer status, String message, T response) {


        ResultInfo<T> result = new ResultInfo<>();
        result.setStatus(status);
        result.setMessage(message);
        result.setResponse(response);
        return result;
    }

//    public ResultInfo success(Integer status, String message, T response) {
//
//        return new ResultInfo(status, message, response);
//    }
//
//    public ResultInfo(Integer status, String message, T response) {
//        this.status = status;
//        this.message = message;
//        this.response = response;
//    }
    // 省略其他代码...
}
