package com.webflux.studywebflux01.repository;

import com.webflux.studywebflux01.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@Repository
public interface UserDao extends ReactiveMongoRepository<User,String> {
    /**
     * 根据用户年龄区间 查询用户信息
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{'$gte': 50,'$lte': 100}}")
    Flux<User> findOldUser();
}
