package com.webflux.studywebflux01.util.System;


import java.io.Serializable;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 封装返回内容 统一返回格式
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private static Integer state = 200;
    /**
     * 状态码对应的消息
     */
    private static String message = "ok";
    /**
     * 服务端返回给客户端的数据
     */
    private static Object data;

    public JsonResult(String message) {
        JsonResult.message = message;
    }

    public JsonResult(Object data) {
        JsonResult.data = data;
    }

    public JsonResult(Throwable e) {
        JsonResult.state = 0;//错误
        JsonResult.message = e.getMessage();
    }

    public JsonResult(Integer state, String message) {
        JsonResult.state = state;
        JsonResult.message = message;
    }

    public JsonResult(Integer state, String message,Object data) {
        JsonResult.state = state;
        JsonResult.message = message;
        JsonResult.data = data;
    }

    public static JsonResult builder(Object data){
        return new JsonResult(data);
    }

    public static JsonResult builder(String message){
        return new JsonResult(message);
    }

    public static JsonResult builder(Throwable e) {
        return new JsonResult(400,e.getMessage());
    }

    public static JsonResult builder(Integer state,String message){
        return new JsonResult(state,message);
    }

    public static JsonResult builder(Integer state,String message,Object data){
        return new JsonResult(state,message,data);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        JsonResult.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        JsonResult.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        JsonResult.data = data;
    }


}