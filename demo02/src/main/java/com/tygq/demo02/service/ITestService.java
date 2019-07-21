package com.tygq.demo02.service;

import com.tygq.demo02.entity.User;
import com.tygq.demo02.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ITestService {
    User getUserById(Integer userId);
    List<User> getAllUsers();

    Page<User> getAllUserPage(PageRequest pageRequest);

    ResultVO addNewUser(User user);

    ResultVO addNewUserForTransaction();

    ResultVO updateUser(User user);

    ResultVO deleteUserById(Integer userId);
}
