package com.tygq.demo02.controller;

import com.tygq.demo02.constants.Constant;
import com.tygq.demo02.entity.User;
import com.tygq.demo02.service.ITestService;
import com.tygq.demo02.utils.ResultVOUtil;
import com.tygq.demo02.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private ITestService testService;

    /**
     * 第一个接口：根据页面传来的用户id查询用户信息，存在返回用户信息，不存在返回提示信息
     * @param id
     * @request http://localhost:8888/demo02/test/getUserById?userId=1
     */
    @RequestMapping("getUserById")
    public ResultVO getUserById(@RequestParam(name = "userId") Integer id){
        User user = testService.getUserById(id);
        if(user == null){
            return ResultVOUtil.error(Constant.ERROR,"查询的用户不存在！");
        }
        return ResultVOUtil.success(user);
    }

    /**
     * 第二个接口：查询所有的用户列表，不分页
     * @request http://localhost:8888/demo02/test/getAllUserList
     */
    @RequestMapping("getAllUserList")
    public ResultVO getAllUserList(){
        List<User> userList = testService.getAllUsers();
        if(userList == null){
            return ResultVOUtil.success("查询的用户列表为空");
        }
        return ResultVOUtil.success(userList);
    }

    /**
     * 第三个接口：分页查询所有的用户
     * @param page
     * @param size
     * @request http://localhost:8888/demo02/test/getAllUserPage?page=0&size=10
     */
    @RequestMapping("getAllUserPage")
    public ResultVO getAllUserPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequest = new PageRequest(page,size);
        Page<User> userPage = testService.getAllUserPage(pageRequest);
        return ResultVOUtil.success(userPage.getContent());
    }

    /**
     * 第四个接口：插入一条用户信息
     * @param user
     * @request http://localhost:8888/demo02/test/addNewUser?name=小新&age=17&classId=2
     */
    @RequestMapping("addNewUser")
    public ResultVO addNewUser(User user){
        return testService.addNewUser(user);
    }

    /**
     * 第五个接口：测试事务是否可以回滚，第一条插入成功之后，第二条插入失败，第一条也应该立即回滚
     * @request http://localhost:8888/demo02/test/addTwoNewUser
     * 此时必定会发生异常，如果事务生效，那么数据库是不会产生任何新的数据的，测试符合预期
     */
    @RequestMapping("addTwoNewUser")
    public ResultVO addTwoNewUser(){
        return testService.addNewUserForTransaction();
    }

    /**
     * 第六个接口：测试更新用户信息
     * @param user
     * @request http://localhost:8888/demo02/test/updateUser?id=4&name=fossi&age=18&classId=1
     */
    @RequestMapping("updateUser")
    public ResultVO updateUser(User user){
        return testService.updateUser(user);
    }

    /**
     * 第七个接口：测试删除一个用户信息，注意必须要加@Transactional注解才能正常执行
     * @param userId
     * @request http://localhost:8888/demo02/test/deleteUserById?userId=4
     */
    @RequestMapping("deleteUserById")
    public ResultVO deleteUserById(Integer userId){
        return testService.deleteUserById(userId);
    }
}
