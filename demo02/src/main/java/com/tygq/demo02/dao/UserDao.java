package com.tygq.demo02.dao;

import com.tygq.demo02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,String> {
    //根据id查询单个用户信息
    User findById(Integer userId);
    void deleteById(Integer userId);
}
