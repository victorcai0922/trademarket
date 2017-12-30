package com.start.demo.service;

import com.start.demo.bean.User;
import com.start.demo.bean.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findUserby(String name) {

        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(name);

        if (hasKey ) {
            User user = operations.get(name);
            System.out.println("----find cache use here---" + user.getName());
            return user;
        }
        User user = userRepository.findAllByName(name);

        operations.set(name,user);
        return user;

    }

//    @Override
//    @Cacheable(value = "usercache",keyGenerator = "wislykeyGenerator")
//    public User findUserby(int Id) {
//        System.out.println("----no cache use here---");
//        return userRepository.findAllById(Id);
//    }
}
