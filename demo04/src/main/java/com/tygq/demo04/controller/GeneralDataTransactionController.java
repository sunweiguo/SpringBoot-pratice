package com.tygq.demo04.controller;

import com.tygq.demo04.constants.Constant;
import com.tygq.demo04.entity.GeneralDataTransaction;
import com.tygq.demo04.service.IGeneralDataTransactionService;
import com.tygq.demo04.utils.ResultVOUtil;
import com.tygq.demo04.vo.MessageVO;
import com.tygq.demo04.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource(value = {"classpath:test.properties"}, ignoreResourceNotFound = true)
public class GeneralDataTransactionController {

    @Autowired
    private IGeneralDataTransactionService generalDataTransactionService;
    @Value("${producer.group.id}")
    private static String producerGroupId;
    @Value("${send.url}")
    private static String sendUrl;

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
        ModelAndView mv = new ModelAndView("kafkaManager/sendMsg");
        mv.addObject("address",sendUrl);
        mv.addObject("topic",producerGroupId);
        return mv;
    }


    /**
     * 发送工单
     */
    @PostMapping("doSendMsg")
    @ResponseBody
    public ResultVO doSendMsg(@Valid MessageVO messageVO, BindingResult bindingResult){
        //对用户输入的参数进行校验，这里简单校验是否为空
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                return ResultVOUtil.error(Constant.ERROR,error.getDefaultMessage());
            }
        }
        return generalDataTransactionService.sendMessage(messageVO);
    }


}
