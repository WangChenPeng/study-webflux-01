package com.webflux.studywebflux01.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@ControllerAdvice
@Log4j2
public class CheckAdvice {

    /**
     * 业务处理时 客户端刷页面 或者退出
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> OffLine(IOException e){
        //og.info("客户端异常断开连接:{}",e.getMessage());
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * flux刷数据时 退出页面
     * @param e
     * @return
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> FluxOffLine(UnsupportedOperationException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
