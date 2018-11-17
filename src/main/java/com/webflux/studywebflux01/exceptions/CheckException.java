package com.webflux.studywebflux01.exceptions;

import lombok.Data;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 自定义异常
 */
@Data
public class CheckException extends RuntimeException{

    private static final long serialVersionUID=1L;

    /**
     * 出错字段的名称
     */
    private String fieldName;

    /**
     * 出错字段的值
     */
    private String fieldValue;

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    protected CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckException(String fieldName, String fieldValue) {
        super();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
