package com.tygq.demo02.service.impl;

import com.tygq.demo02.constants.Constant;
import com.tygq.demo02.dao.UserDao;
import com.tygq.demo02.entity.User;
import com.tygq.demo02.service.ITestService;
import com.tygq.demo02.utils.ResultVOUtil;
import com.tygq.demo02.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService implements ITestService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.findById(userId);
    }

    /**
     * 1、返回所有的用户列表
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    @Override
    public Page<User> getAllUserPage(PageRequest pageRequest) {
        Page<User> userPage = userDao.findAll(pageRequest);
        return new PageImpl<User>(userPage.getContent(),pageRequest,userPage.getTotalElements());
    }

    @Override
    public ResultVO addNewUser(User user) {
        User u = new User();
        BeanUtils.copyProperties(user,u);
        try {
            userDao.save(u);
        } catch (Exception e){
            return ResultVOUtil.error(Constant.ERROR,"保存失败啦！");
        }
        return ResultVOUtil.success("保存用户成功！");
    }

    @Override
    @Transactional
    public ResultVO addNewUserForTransaction() {
        User user = new User();
        user.setId(5);
        user.setName("事务1");
        userDao.save(user);

        //强行发生异常
        int res = 1/0;

        return ResultVOUtil.success("插入成功");
    }

    @Override
    public ResultVO updateUser(User user) {
        if(user == null || user.getId() == null){
            return ResultVOUtil.error(Constant.ERROR,"输入参数有误");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setClassId(user.getClassId());
        try {
            userDao.save(updateUser);
        } catch (Exception e){
            return ResultVOUtil.error(Constant.ERROR,"更新用户信息失败");
        }
        return ResultVOUtil.success(updateUser);
    }

    @Override
    @Transactional
    public ResultVO deleteUserById(Integer userId) {
        if(userId == null){
            return ResultVOUtil.error(Constant.ERROR,"输入参数有误");
        }
        try {
            userDao.deleteById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(Constant.ERROR,"删除该用户信息失败");
        }
        return ResultVOUtil.success("删除用户信息成功");
    }
}
