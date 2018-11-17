package com.webflux.studywebflux01.advice;

import com.webflux.studywebflux01.exceptions.CheckException;
import com.webflux.studywebflux01.util.System.JsonResult;
import com.webflux.studywebflux01.util.System.ResponseBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@RestControllerAdvice
@Log4j2
public class CheckAdvice {

    /**
     * 业务处理时 客户端刷页面 或者退出
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> OffLine(IOException e) {
        log.info("客户端异常断开连接:{}",e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * flux刷数据时 退出页面
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> FluxOffLine(UnsupportedOperationException e) {
        log.info("flux未结束,客户端异常断开连接:{}",e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理名称校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CheckException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, Object> catchCheckException(CheckException e) {
        log.info("名称校验异常");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", 400);
        stringObjectHashMap.put("msg", "字段: "+ e.getFieldName()+" 校验失败 请更换名称重试");
        return stringObjectHashMap;
    }
    //WebExchangeBindException

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult catchValidationException(WebExchangeBindException e) {
        /*for (FieldError fieldError : e.getFieldErrors()) {
            System.out.println(fieldError.getDefaultMessage());
            System.out.println(fieldError.getField());
            System.out.println(fieldError.getRejectedValue());
            System.out.println(fieldError.getCode());
        }
        log.info("名称校验异常");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", 400);
        stringObjectHashMap.put("msg", "字段: "+ "e.getFieldName()"+" 校验失败 请更换名称重试");*/

        return JsonResult.builder(400, e.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",","","")));
    }

}
