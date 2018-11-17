package com.webflux.studywebflux01.repository;

import com.webflux.studywebflux01.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe:
 */
@Repository
public interface UserDao extends ReactiveMongoRepository<User,String> {
}
