package com.webflux.studywebflux01.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 用户实体类
 */
@Data
@Document(collection = "user")
public class User {

    /**
     * @NotNull://CharSequence, Collection, Map 和 Array 对象不能是 null, 但可以是空集（size = 0）。  
     * @NotEmpty://CharSequence, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。  
     * @NotBlank://String 不是 null 且去除两端空白字符后的长度（trimmed length）大于 0。 
     */

    /**
     * 数据库id
     */
    @Id
    private String id;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;
}
