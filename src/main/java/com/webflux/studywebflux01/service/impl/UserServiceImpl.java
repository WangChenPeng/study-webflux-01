package com.webflux.studywebflux01.service.impl;

import com.webflux.studywebflux01.entity.User;
import com.webflux.studywebflux01.repository.UserDao;
import com.webflux.studywebflux01.service.UserService;
import com.webflux.studywebflux01.util.CheckName;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    /**
     * 构造方法
     * @param userDao
     */
    private UserServiceImpl(UserDao userDao){
        System.out.println("spring 目前推荐使用此种方式注入 避免使用autoWired");
        this.userDao=userDao;
    }



    /**
     * 保存用户信息
     * @param user 包含用户信息
     */
    @Override
    public Mono<User> addUser(User user) {
        // spring data jpa 里面, 新增和修改都是save. 有id是修改, id为空是新增
        // 根据实际情况是否置空id
        user.setId(null);
        CheckName.checkName(user.getName());
        return userDao.save(user);
    }
}
