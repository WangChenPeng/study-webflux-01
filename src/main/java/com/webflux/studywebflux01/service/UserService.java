package com.webflux.studywebflux01.service;

import com.webflux.studywebflux01.entity.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
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

    /**
     * 根据id 删除用户信息
     * @param id
     */
    Mono<ResponseEntity<Void>> deleteUser(String id);

    /**
     * 根据id 更新用户信息
     * @param id 用户id
     * @param user 更新的用户信息
     * @return
     */
    Mono<ResponseEntity<User>> updateUser(String id, User user);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    Mono<ResponseEntity<User>> findUserById(String id);

    /**
     * 根据用户年龄区间 查询用户信息
     * @param start
     * @param end
     * @return
     */
    Flux<User> findUserByAgeBetween(int start, int end);

    /**
     * 查询老年用户
     * @return
     */
    Flux<User> findOldUser();

}
