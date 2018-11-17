package com.webflux.studywebflux01.controller;

import com.webflux.studywebflux01.entity.User;
import com.webflux.studywebflux01.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * @param userService
     */
    public UserAsyncController(UserService userService){
        System.out.println("spring 目前推荐使用此种方式注入 避免使用autoWired");
        this.userService=userService;
    }

    /**
     * 增加用户的接口
     * @param user
     * @return
     */
    @PostMapping(value = "/add")
    public Mono<User> addUser(@Valid User user){
        log.info("UserAsyncController/add 入参 : {}",user);
        Mono<User> userMono = userService.addUser(user);
        return userMono;
    }


}
