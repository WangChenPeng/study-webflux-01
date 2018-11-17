package com.webflux.studywebflux01.util;

import com.webflux.studywebflux01.exceptions.CheckException;

import java.util.stream.Stream;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 检查名称的工具类
 */
public class CheckName {

    private static final String[] INVALID_NAMES ={ "admin","guanliyuan"};

    /**
     * 校验名称,不成功抛出校验异常
     * @param value
     */
    public static void checkName(String value){
        Stream.of(INVALID_NAMES).filter(name->name.equals(value))
                .findAny().ifPresent(name->{
                    throw new CheckException("name",value);
        });
    }

}
