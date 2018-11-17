package com.webflux.studywebflux01.service;

import com.webflux.studywebflux01.entity.User;
import reactor.core.publisher.Mono;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 用户相关的业务接口
 */
public interface UserService {
    /**
     * 保存用户信息
     * @param user 包含用户信息
     */
    Mono<User> addUser(User user);
}
