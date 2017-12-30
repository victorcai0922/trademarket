package com.start.demo.bean;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findAllByName(String name);
//    User findAllById(int Id);
}
