package com.webflux.studywebflux01.controller;

import com.webflux.studywebflux01.entity.User;
import com.webflux.studywebflux01.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@RequestMapping("/user")
@Log4j2
@RestController
public class UserAsyncController {

    private UserService userService;

    /**
     * 构造方法
     * @param userService service接口
     */
    public UserAsyncController(UserService userService){
        System.out.println("spring 目前推荐使用此种方式注入 避免使用autoWired");
        this.userService=userService;
    }

    /**
     * 增加用户的接口
     * @param user 用户信息
     */
    @PostMapping(value = "/add")
    public Mono<User> addUser(@Valid User user){
        log.info("UserAsyncController/addUser 入参 : {}",user);
        Mono<User> userMono = userService.addUser(user);
        return userMono;
    }

    /**
     * 根据id删除用户 存在的时候返回200 不存在返回404
     * @param id 用户id
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id){
        log.info("UserAsyncController/deleteUser 入参 : {}",id);
        return userService.deleteUser(id);
    }

    /**
     * 根据用户id 进行更新用户信息
     * @param id 用户id
     * @param user 用户信息
     */
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable String id,@Valid User user){
        log.info("UserAsyncController/updateUser 入参 : {}",id);
        return userService.updateUser(id,user);
    }

    /**
     * 根据用户id查找用户,存在返回用户信息,不存在返回404
     */
    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable String id){
        log.info("UserAsyncController/findUserById 入参 : {}",id);
        return userService.findUserById(id);
    }

    /**
     * 根据年龄查找用户
     */
    @GetMapping("/findByAge/{start}/{end}")
    public Flux<User> findUserByAge(@PathVariable Integer start,@PathVariable Integer end){
        log.info("UserAsyncController/findUserByAge 入参 start : {} , end : {}",start,end);
        return userService.findUserByAgeBetween(start,end);
    }

    /**
     * 根据年龄查找用户
     */
    @GetMapping(value = "/findByAgeStream/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findUserByAgeStream(@PathVariable Integer start,@PathVariable Integer end){
        log.info("UserAsyncController/findUserByAgeStream 入参 start : {} , end : {}",start,end);
        return userService.findUserByAgeBetween(start,end);
    }

    /**
     * 查询老年用户
     */
    @GetMapping("/findOldUser")
    public Flux<User> findOldUser(){
        log.info("UserAsyncController/findOldUser 入参");
        return userService.findOldUser();
    }

    /**
     * 查询老年用户
     */
    @GetMapping(value = "/findOldUserStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findOldUserStream(){
        log.info("UserAsyncController/findOldUserStream 入参");
        return userService.findOldUser();
    }
}
