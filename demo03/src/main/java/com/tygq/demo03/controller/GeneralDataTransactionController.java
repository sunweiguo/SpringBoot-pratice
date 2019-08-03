package com.tygq.demo03.controller;

import com.tygq.demo03.constants.Constant;
import com.tygq.demo03.entity.GeneralDataTransaction;
import com.tygq.demo03.service.IGeneralDataTransactionService;
import com.tygq.demo03.utils.ResultVOUtil;
import com.tygq.demo03.vo.MessageVO;
import com.tygq.demo03.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/sendMsgManager")
public class GeneralDataTransactionController {

    @Autowired
    private IGeneralDataTransactionService generalDataTransactionService;

    /**
     * 已发送的工单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "20") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<GeneralDataTransaction> generalDataTransactionPage = generalDataTransactionService.findAll(request);
        map.put("generalDataTransactionPage", generalDataTransactionPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("kafkaManager/sendMsgList", map);
    }

    /**
     * 发送工单的页面
     * @return
     */
    @GetMapping("sendMsgPage")
    public ModelAndView sendMsgPage(){
        return new ModelAndView("kafkaManager/sendMsg");
    }


    /**
     * 发送工单
     */
    @PostMapping("doSendMsg")
    @ResponseBody
    public ResultVO doSendMsg(@Valid MessageVO messageVO, BindingResult bindingResult){
        System.out.println(messageVO);
        //对用户输入的参数进行校验，这里简单校验是否为空
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                return ResultVOUtil.error(Constant.ERROR,error.getDefaultMessage());
            }
        }
        return generalDataTransactionService.sendMessage(messageVO);
    }


}
