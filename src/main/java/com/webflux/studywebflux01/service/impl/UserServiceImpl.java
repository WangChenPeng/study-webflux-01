package com.webflux.studywebflux01.service.impl;

import com.webflux.studywebflux01.entity.User;
import com.webflux.studywebflux01.repository.UserDao;
import com.webflux.studywebflux01.service.UserService;
import com.webflux.studywebflux01.util.CheckName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    /**
     * 根据用户id 删除用户信息
     * @param id
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteUser(String id) {
        return  userDao.findById(id).flatMap(user -> userDao.delete(user)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id 更新用户信息
     * @param id 用户id
     * @param user 更新的用户信息
     * @return
     */
    @Override
    public Mono<ResponseEntity<User>> updateUser(String id, User user) {
        //校验用户名是否合法
        CheckName.checkName(user.getName());
        //执行查询和保存业务逻辑
        return userDao.findById(id).flatMap(u->{
            u.setAge(user.getAge());
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            return userDao.save(u);
            //构建返回对象
        }).map(u->new ResponseEntity<User>(u,HttpStatus.OK))
                //如果为空则构建相应的返回对象
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据用户id查询 用户信息
     * @param id
     * @return
     */
    @Override
    public Mono<ResponseEntity<User>> findUserById(String id) {
        return userDao.findById(id).map(u->new ResponseEntity<User>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据用户年龄取件 查询用户信息
     * @param start
     * @param end
     * @return
     */
    @Override
    public Flux<User> findUserByAgeBetween(int start, int end) {
        return userDao.findByAgeBetween(start,end);
    }

    @Override
    public Flux<User> findOldUser() {
        return userDao.findOldUser();
    }
}
